package com.softsquared.softsquared_daum_cafe.src.main.fragments.popular;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.BaseFragment;
import com.softsquared.softsquared_daum_cafe.src.main.MainActivity;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.popular.adapter.PopularArticlesPager;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.popular.fragments.adapter.PopularArticleListAdapter;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.popular.interfaces.PopularFragmentView;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.popular.models.Article;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.popular.models.PopularResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import static com.softsquared.softsquared_daum_cafe.src.BaseActivity.dpUnit;

public class PopularFragment extends BaseFragment implements PopularFragmentView {

    private Toolbar tbFavorite;
    private DrawerLayout dlPopular;
    private CoordinatorLayout clPopular;
    private LinearLayout llSelectNowPopular;
    private LinearLayout llSelectWeeklyPopular;
    private LinearLayout llSelectMonthlyPopular;
    private TextView tvToolbarTitleCollapsed;
    private TextView tvToolbarTitleExpanded;
    private TextView tvToolbarSubTitleExpanded;
    private LinearLayout llToolbarTitleContainer;
    private AppBarLayout ablPopular;
    private ImageView ivBackgroundPopular;
    private ImageView ivBlackboxPopular;
    private ViewPager vpPopular;
    private TextView tvCurrentHourDrawer;
    private TextView tvCurrentHourDescDrawer;

    private Context mContext;
    private PopularArticlesPager paPager;

    private int todayHour;
    private SimpleDateFormat sdfCurrentHour = new SimpleDateFormat("a hh:00", Locale.ENGLISH);
    private Calendar today;
    private int articlesViewType = PopularArticleListAdapter.VIEWTYPE_DEFAULT;

    // articles
    private ArrayList<ArrayList<PopularResponse.Result>> articles = new ArrayList<>();
    private CoordinatorLayout.LayoutParams vpLayoutParams;

    public PopularFragment() {
    }

    public static PopularFragment newInstance() {
        PopularFragment fragment = new PopularFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular, container, false);

        // Constants - Today
        today = Calendar.getInstance();
        todayHour = today.get(Calendar.HOUR);
        if (todayHour == 0)
            todayHour = 12;

        /* findViewByID */
        tbFavorite = view.findViewById(R.id.toolbar_popular);
        dlPopular = view.findViewById(R.id.dl_popular);
        llSelectNowPopular = view.findViewById(R.id.drawer_item1_popular);
        llSelectWeeklyPopular = view.findViewById(R.id.drawer_item2_popular);
        llSelectMonthlyPopular = view.findViewById(R.id.drawer_item3_popular);
        ablPopular = view.findViewById(R.id.abl_popular);
        vpPopular = view.findViewById(R.id.vp_popular);
        tvToolbarTitleCollapsed = view.findViewById(R.id.tv_toolbar_title_collapsed_popular);
        tvToolbarTitleExpanded = view.findViewById(R.id.tv_toolbar_title_expanded_popular);
        tvToolbarSubTitleExpanded = view.findViewById(R.id.tv_toolbar_subtitle_expanded_popular);
        llToolbarTitleContainer = view.findViewById(R.id.ll_toolbar_title_container_expanded_popular);
        tvCurrentHourDrawer = view.findViewById(R.id.tv_current_hour_popular_drawer);
        tvCurrentHourDescDrawer = view.findViewById(R.id.tv_current_hour_desc_popular_drawer);
        ivBackgroundPopular = view.findViewById(R.id.iv_toolbar_background_popular);
        ivBlackboxPopular = view.findViewById(R.id.iv_blackbox_toolbar_popular);
        clPopular = view.findViewById(R.id.cl_popular);

        /* Toolbar */
        setHasOptionsMenu(true);
        ((MainActivity) mContext).setSupportActionBar(tbFavorite);
        ActionBar actionBar = ((MainActivity) mContext).getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_popular);

        /* AppBarLayout Add On Offset Change Listener */
        ablPopular.addOnOffsetChangedListener(this);

        /* DrawerLayout Slide Listener */
        dlPopular.addDrawerListener(this);

        // articles data
        ArrayList<PopularResponse.Result> dummy1 = new ArrayList<>();
        dummy1.add(new PopularResponse.Result("", "", ""));
        dummy1.add(new PopularResponse.Result("", "", ""));
        dummy1.add(new PopularResponse.Result("", "", ""));
        dummy1.add(new PopularResponse.Result("", "", ""));
        dummy1.add(new PopularResponse.Result("", "", ""));
        ArrayList<PopularResponse.Result> dummy2 = new ArrayList<>();
        dummy2.add(new PopularResponse.Result("", "", ""));
        dummy2.add(new PopularResponse.Result("", "", ""));
        dummy2.add(new PopularResponse.Result("", "", ""));
        dummy2.add(new PopularResponse.Result("", "", ""));
        dummy2.add(new PopularResponse.Result("", "", ""));
        ArrayList<PopularResponse.Result> dummy3 = new ArrayList<>();
        dummy3.add(new PopularResponse.Result("", "", ""));
        dummy3.add(new PopularResponse.Result("", "", ""));
        dummy3.add(new PopularResponse.Result("", "", ""));
        dummy3.add(new PopularResponse.Result("", "", ""));
        dummy3.add(new PopularResponse.Result("", "", ""));
        articles.add(dummy1);
        articles.add(dummy2);
        articles.add(dummy3);

        /* ViewPager */
        paPager = new PopularArticlesPager(getChildFragmentManager(), articles, articlesViewType);
        vpPopular.setAdapter(paPager);
        vpPopular.setCurrentItem(150);
        vpPopular.addOnPageChangeListener(this);
        vpLayoutParams = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        vpLayoutParams.setBehavior(new AppBarLayout.ScrollingViewBehavior());


        /* Set On Click Listener */
        llSelectMonthlyPopular.setOnClickListener(this);
        llSelectWeeklyPopular.setOnClickListener(this);
        llSelectNowPopular.setOnClickListener(this);

        /* Set View init */
        tvToolbarTitleExpanded.setText(todayHour + "시, 인기글");
        tvToolbarTitleCollapsed.setText(todayHour + "시, 인기글");
        tvToolbarSubTitleExpanded.setText(getString(R.string.drawer_popular_now_subtitle));
        tvCurrentHourDrawer.setText(sdfCurrentHour.format(today.getTime()));
        tvCurrentHourDescDrawer.setText("지금, " + todayHour + "시의 인기글입니다.");
        setPopularBackground(todayHour);

        /* get Articles From Server */
        getArticles();

        return view;
    }

    private void setPopularBackground(int hour) {
        switch (hour) {
            case 1:
                ivBackgroundPopular.setImageResource(R.drawable.iv_popular_background1);
                break;
            case 2:
                ivBackgroundPopular.setImageResource(R.drawable.iv_popular_background2);
                break;
            case 3:
                ivBackgroundPopular.setImageResource(R.drawable.iv_popular_background3);
                break;
            case 4:
                ivBackgroundPopular.setImageResource(R.drawable.iv_popular_background4);
                break;
            case 5:
                ivBackgroundPopular.setImageResource(R.drawable.iv_popular_background5);
                break;
            case 6:
                ivBackgroundPopular.setImageResource(R.drawable.iv_popular_background6);
                break;
            case 7:
                ivBackgroundPopular.setImageResource(R.drawable.iv_popular_background7);
                break;
            case 8:
                ivBackgroundPopular.setImageResource(R.drawable.iv_popular_background6);
                break;
            case 9:
                ivBackgroundPopular.setImageResource(R.drawable.iv_popular_background5);
                break;
            case 10:
                ivBackgroundPopular.setImageResource(R.drawable.iv_popular_background4);
                break;
            case 11:
                ivBackgroundPopular.setImageResource(R.drawable.iv_popular_background3);
                break;
            case 12:
                ivBackgroundPopular.setImageResource(R.drawable.iv_popular_background2);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                dlPopular.openDrawer(GravityCompat.START);
                break;
            case R.id.tb_imagelist_popular:
                articlesViewType = (articlesViewType == PopularArticleListAdapter.VIEWTYPE_IMAGE) ? PopularArticleListAdapter.VIEWTYPE_NOIMAGE : PopularArticleListAdapter.VIEWTYPE_IMAGE;
                if (articlesViewType == PopularArticleListAdapter.VIEWTYPE_NOIMAGE) {
                    item.setIcon(R.drawable.ic_image_list_disabled);
                } else if (articlesViewType == PopularArticleListAdapter.VIEWTYPE_IMAGE) {
                    item.setIcon(R.drawable.ic_image_list);
                }
                paPager = new PopularArticlesPager(getChildFragmentManager(), articles, articlesViewType);
                vpPopular.setAdapter(paPager);
                vpPopular.setCurrentItem(150);
                break;
        }
        return true;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_toolbar_popular, menu);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.drawer_item1_popular:
                vpPopular.setCurrentItem(150);
                dlPopular.closeDrawers();
                break;
            case R.id.drawer_item2_popular:
                vpPopular.setCurrentItem(151);
                dlPopular.closeDrawers();
                break;
            case R.id.drawer_item3_popular:
                vpPopular.setCurrentItem(152);
                dlPopular.closeDrawers();
                break;
        }
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        // 0 <= i <= -appBar.getTotalScrollRange()
        float collapsedTitleAlpha = 1.0f - Math.abs((i + appBarLayout.getTotalScrollRange()) * 2.5f / (float) appBarLayout.getTotalScrollRange());
        if (collapsedTitleAlpha <= 0.0f)
            collapsedTitleAlpha = 0.0f;
        else if (collapsedTitleAlpha >= 1.0f)
            collapsedTitleAlpha = 1.0f;
        float expandedTitleAlpha = Math.abs((i + appBarLayout.getTotalScrollRange()) * 2.5f / (float) appBarLayout.getTotalScrollRange()) - 1.0f;
        if (expandedTitleAlpha <= 0.0f)
            expandedTitleAlpha = 0.0f;
        else if (expandedTitleAlpha >= 1.0f) {
            expandedTitleAlpha = 1.0f;
        }
        tvToolbarTitleCollapsed.setAlpha(collapsedTitleAlpha);
        llToolbarTitleContainer.setAlpha(expandedTitleAlpha);
        ivBlackboxPopular.setAlpha(0.4f - Math.abs((i + appBarLayout.getTotalScrollRange()) * 0.2f / (float) appBarLayout.getTotalScrollRange())); // Alpha : 0.2 ~ 0.4

        // viewPager 마진 동적 변경
        vpLayoutParams.topMargin = (int) (-48 * dpUnit * (Math.abs((i + appBarLayout.getTotalScrollRange()) / (float) appBarLayout.getTotalScrollRange())));
        vpPopular.setLayoutParams(vpLayoutParams);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position % 3) {
            /* Set View */
            case 0:
                tvToolbarTitleExpanded.setText(todayHour + "시, 인기글");
                tvToolbarTitleCollapsed.setText(todayHour + "시, 인기글");
                tvCurrentHourDrawer.setText(sdfCurrentHour.format(today.getTime()));
                tvCurrentHourDescDrawer.setText("지금, " + todayHour + "시의 인기글입니다.");
                tvToolbarSubTitleExpanded.setText(getString(R.string.drawer_popular_now_subtitle));
                tvToolbarSubTitleExpanded.setLetterSpacing(0.8f);
                // ImageView FadeIn FadeOut
                ivBackgroundPopular.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fadeout_half));
                setPopularBackground(todayHour);
                ivBackgroundPopular.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fadein_half));
                break;
            case 1:
                tvToolbarTitleExpanded.setText(getString(R.string.drawer_popular_week));
                tvToolbarTitleCollapsed.setText(getString(R.string.drawer_popular_week));
                tvToolbarSubTitleExpanded.setText(getString(R.string.drawer_popular_week_subtitle));
                tvToolbarSubTitleExpanded.setLetterSpacing(0.5f);
                // ImageView FadeIn FadeOut
                ivBackgroundPopular.setImageResource(R.drawable.iv_popular_background_week);
                ivBackgroundPopular.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fadeout_half));
                ivBackgroundPopular.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fadein_half));

                break;
            case 2:
                tvToolbarTitleExpanded.setText(getString(R.string.drawer_popular_month));
                tvToolbarTitleCollapsed.setText(getString(R.string.drawer_popular_month));
                tvToolbarSubTitleExpanded.setText(getString(R.string.drawer_popular_month_subtitle));
                tvToolbarSubTitleExpanded.setLetterSpacing(0.3f);
                // ImageView FadeIn FadeOut
                ivBackgroundPopular.setImageResource(R.drawable.iv_popular_background_month);
                ivBackgroundPopular.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fadeout_half));
                ivBackgroundPopular.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fadein_half));
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
        clPopular.setTranslationX((dlPopular.getWidth() - 84 * dpUnit) * slideOffset);
    }

    @Override
    public void onDrawerOpened(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerClosed(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    @Override
    public void validateSuccess(ArrayList<PopularResponse.Result> results) {
        hideProgressDialog();
        articles.clear();
        articles.add(results);
        articles.add(results);
        articles.add(results);
        if (articlesViewType == PopularArticleListAdapter.VIEWTYPE_DEFAULT)
            articlesViewType = PopularArticleListAdapter.VIEWTYPE_IMAGE;
        paPager = new PopularArticlesPager(getChildFragmentManager(), articles, articlesViewType);
        vpPopular.setAdapter(paPager);
        vpPopular.setCurrentItem(150);
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showToast((message == null) ? getString(R.string.network_error) : message);
    }

    public void getArticles() {
        showProgressDialog();
        final PopularService popularService = new PopularService(this);
        popularService.getPopularArticles();
    }
}
