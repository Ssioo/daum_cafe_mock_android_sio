package com.softsquared.softsquared_daum_cafe.src.articledetail;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.BaseActivity;
import com.softsquared.softsquared_daum_cafe.src.articledetail.adapter.CommentListAdapter;
import com.softsquared.softsquared_daum_cafe.src.articledetail.interfaces.ArticleDetailActivityView;
import com.softsquared.softsquared_daum_cafe.src.articledetail.models.ArticleDetailResponse;
import com.softsquared.softsquared_daum_cafe.src.articledetail.models.Comment;
import com.softsquared.softsquared_daum_cafe.src.cafe.mypage.MyPageActivity;
import com.softsquared.softsquared_daum_cafe.src.cafe.mysetting.MySettingActivity;
import com.softsquared.softsquared_daum_cafe.src.common.util.RecyclerViewDecoration;

import java.util.ArrayList;

import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.USER_ID;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.USER_LOGINNED;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.sSharedPreferences;

public class ArticleDetailActivity extends BaseActivity implements ArticleDetailActivityView {

    private SwipeRefreshLayout srlActicleDetail;
    private Toolbar tbArticleDetail;
    private TextView tvArticleTitle;
    private TextView tvArticleAuthor;
    private TextView tvArticleContents;
    private TextView tvArticleCreatedAt;
    private RecyclerView rvComments;
    private DrawerLayout dlArticleDetail;
    private ImageView ivCloseDrawer;
    private TextView tvHome;
    private TextView tvAdmin;
    private TextView tvChat;
    private TextView tvSearch;
    private AppCompatImageButton ivCloseItem1;
    private AppCompatImageButton ivCloseItem2;
    private AppCompatImageButton ivCloseItem3;
    private RecyclerView rvBoardList1;
    private RecyclerView rvBoardList2;
    private RecyclerView rvBoardList3;
    private LinearLayout llProfileDrawer;
    private TextView tvUserName;
    private AppCompatImageButton ibtnSetting;

    private boolean DRAWER_ITEM1_OPENED = true;
    private boolean DRAWER_ITEM2_OPENED = true;
    private boolean DRAWER_ITEM3_OPENED = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articledetail);

        /* findViewByID */
        srlActicleDetail = findViewById(R.id.srl_articledetail);
        tbArticleDetail = findViewById(R.id.toolbar_articledetail);
        dlArticleDetail = findViewById(R.id.dl_articledetail);
        ivCloseDrawer = findViewById(R.id.iv_close_articledetail_drawer);
        ibtnSetting = findViewById(R.id.ibtn_setting_articledetail_drawer);
        llProfileDrawer = findViewById(R.id.ll_profile_articledetail_drawer);
        tvHome = findViewById(R.id.tv_home_articledetail_drawer);
        tvAdmin = findViewById(R.id.tv_admin_articledetail_drawer);
        tvChat = findViewById(R.id.tv_chat_articledetail_drawer);
        tvSearch = findViewById(R.id.tv_search_articledetail_drawer);
        ivCloseItem1 = findViewById(R.id.ibtn_close_boardlist1_articledetail_drawer);
        ivCloseItem2 = findViewById(R.id.ibtn_close_boardlist2_articledetail_drawer);
        ivCloseItem3 = findViewById(R.id.ibtn_close_boardlist3_articledetail_drawer);
        rvBoardList1 = findViewById(R.id.rv_boardlist1_articledetail);
        rvBoardList2 = findViewById(R.id.rv_boardlist2_articledetail);
        rvBoardList3 = findViewById(R.id.rv_boardlist3_articledetail);
        tvUserName = findViewById(R.id.tv_username_articledetail_drawer);
        rvComments = findViewById(R.id.rv_comments_articledetail);
        tvArticleTitle = findViewById(R.id.tv_title_article_detail);
        tvArticleAuthor = findViewById(R.id.tv_author_articledetail);
        tvArticleContents = findViewById(R.id.tv_contents_article_detail);
        tvArticleCreatedAt = findViewById(R.id.tv_article_createtime_articledetail);

        /* Get Contents From Server */
        getContents(1);

        /* Toolbar*/
        setSupportActionBar(tbArticleDetail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_chevron_left_black);

        /* RefreshLayout */
        srlActicleDetail.setOnRefreshListener(this);

        /* RecyclerView */
        // dummy
        ArrayList<Comment> dummy = new ArrayList<>();
        dummy.add(new Comment("Test", "Description1", "Sio", "2019.11.14", ""));
        dummy.add(new Comment("Test", "Description2", "Boyumi", "2019.11.14", ""));
        dummy.add(new Comment("Test", "Description3", "Sio", "2019.11.14", ""));
        rvComments.setAdapter(new CommentListAdapter(dummy, this));
        rvComments.addItemDecoration(new RecyclerViewDecoration(1, 0));

        /* Set On Click Listener */
        ivCloseDrawer.setOnClickListener(this);
        ibtnSetting.setOnClickListener(this);
        llProfileDrawer.setOnClickListener(this);
        tvHome.setOnClickListener(this);
        tvAdmin.setOnClickListener(this);
        tvChat.setOnClickListener(this);
        tvSearch.setOnClickListener(this);
        ivCloseItem1.setOnClickListener(this);
        ivCloseItem2.setOnClickListener(this);
        ivCloseItem3.setOnClickListener(this);

        /* Set View */
        if (sSharedPreferences.getBoolean(USER_LOGINNED, false))
            tvUserName.setText(sSharedPreferences.getString(USER_ID, ""));
        else
            tvUserName.setText("로그인 해주세요.");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_articledetail , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.tb_bookmark_articledetail:
                showToast(getString(R.string.nofunction));
                break;
            case R.id.tb_menu_articledetail:
                dlArticleDetail.openDrawer(Gravity.RIGHT);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        srlActicleDetail.setRefreshing(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibtn_setting_articledetail_drawer:
                if (sSharedPreferences.getBoolean(USER_LOGINNED, false))
                    startNextActivity(MySettingActivity.class);
                else
                    mLoginAlert.show();
                break;
            case R.id.ll_profile_articledetail_drawer:
                if (sSharedPreferences.getBoolean(USER_LOGINNED, false))
                    startNextActivity(MyPageActivity.class);
                else
                    mLoginAlert.show();
                break;
            case R.id.tv_home_articledetail_drawer:
                finish();
                break;
            case R.id.tv_admin_articledetail_drawer:
                showToast(getString(R.string.nofunction));
                break;
            case R.id.tv_chat_articledetail_drawer:
                showToast(getString(R.string.nofunction));
                break;
            case R.id.tv_search_articledetail_drawer:
                showToast(getString(R.string.nofunction));
                break;
            case R.id.ibtn_close_boardlist1_articledetail_drawer:
                if (DRAWER_ITEM1_OPENED) {
                    ivCloseItem1.setImageResource(R.drawable.ic_expand_more);
                    rvBoardList1.setVisibility(View.GONE);
                } else {
                    ivCloseItem1.setImageResource(R.drawable.ic_expand_less);
                    rvBoardList1.setVisibility(View.VISIBLE);
                }
                DRAWER_ITEM1_OPENED = !DRAWER_ITEM1_OPENED;
                return;
            case R.id.ibtn_close_boardlist2_articledetail_drawer:
                if (DRAWER_ITEM2_OPENED) {
                    ivCloseItem2.setImageResource(R.drawable.ic_expand_more);
                    rvBoardList2.setVisibility(View.GONE);
                } else {
                    ivCloseItem2.setImageResource(R.drawable.ic_expand_less);
                    rvBoardList2.setVisibility(View.VISIBLE);
                }
                DRAWER_ITEM2_OPENED = !DRAWER_ITEM2_OPENED;
                return;
            case R.id.ibtn_close_boardlist3_articledetail_drawer:
                if (DRAWER_ITEM3_OPENED) {
                    ivCloseItem3.setImageResource(R.drawable.ic_expand_more);
                    rvBoardList3.setVisibility(View.GONE);
                } else {
                    ivCloseItem3.setImageResource(R.drawable.ic_expand_less);
                    rvBoardList3.setVisibility(View.VISIBLE);
                }
                DRAWER_ITEM3_OPENED = !DRAWER_ITEM3_OPENED;
                return;
            case R.id.iv_close_articledetail_drawer:
                break;
        }
        dlArticleDetail.closeDrawers();
    }

    private void getContents(int boardId) {
        final ArticleDetailService articleDetailService = new ArticleDetailService(this);
        articleDetailService.getArticleDetail(boardId);
    }

    @Override
    public void validateSuccess(ArrayList<ArticleDetailResponse.Result> results) {
        /* Set View */
        tvArticleAuthor.setText(results.get(0).getUserId());
        tvArticleTitle.setText(results.get(0).getTitle());
        tvArticleContents.setText(results.get(0).getCotents());
        tvArticleCreatedAt.setText(results.get(0).getCreatedAt());
    }

    @Override
    public void validateFailure(String message) {
        showToast("내용을 불러오는데 실패하였습니다.");
    }
}
