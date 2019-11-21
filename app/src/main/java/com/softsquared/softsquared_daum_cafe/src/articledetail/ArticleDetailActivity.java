package com.softsquared.softsquared_daum_cafe.src.articledetail;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
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

import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.USER_EMAIL;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.USER_LOGINNED;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.sSharedPreferences;

public class ArticleDetailActivity extends BaseActivity implements ArticleDetailActivityView {

    private SwipeRefreshLayout srlActicleDetail;
    private SwipeRefreshLayout srlBoardArticleDetail;
    private Toolbar tbArticleDetail;
    private TextView tvArticleCategoty;
    private TextView tvArticleTitle;
    private TextView tvArticleAuthor;
    private TextView tvArticleContents;
    private TextView tvArticleCreatedAt;
    private TextView tvArticleViewCount;
    private TextView tvArticleCommentCount;
    private ImageView ivImgArticle;
    private RecyclerView rvComments;
    private TextView tvInputComment;
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
    private ImageView ivCommentCountBackground;
    private ConstraintLayout clInputCommentContainer;
    private LinearLayout llBottomNavContainer;
    private TextView tvInputCommentHeader;
    private LinearLayout llWriteComment;
    private TextView tvSubmitComment;
    private EditText etComment;
    private NestedScrollView nsvArticleDetail;

    private boolean DRAWER_ITEM1_OPENED = true;
    private boolean DRAWER_ITEM2_OPENED = true;
    private boolean DRAWER_ITEM3_OPENED = true;
    private boolean INPUT_COMMENT_OPENED = false;

    private int mBoardId;
    private int mArticleViewCount;
    private int mCommentCount;
    private String mCategoryType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articledetail);

        /* Get Intent */
        Intent intent = getIntent();
        mBoardId = intent.getIntExtra("boardId", 0);
        mArticleViewCount = intent.getIntExtra("viewCount", 0);
        mCommentCount = intent.getIntExtra("commentCount", 0);
        mCategoryType = intent.getStringExtra("categoryType");

        /* findViewByID */
        srlActicleDetail = findViewById(R.id.srl_articledetail);
        srlBoardArticleDetail = findViewById(R.id.srl_drawer_articledetail_drawer);
        tbArticleDetail = findViewById(R.id.toolbar_articledetail);
        dlArticleDetail = findViewById(R.id.dl_articledetail);
        tvArticleCategoty = findViewById(R.id.tv_board_title_article_detail);
        ivImgArticle = findViewById(R.id.iv_img_article_detail);
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
        tvArticleViewCount = findViewById(R.id.tv_viewcount_article_detail);
        tvArticleCommentCount = findViewById(R.id.tv_comment_count_article_detail);
        ivCommentCountBackground = findViewById(R.id.iv_comment_count_background_articledetail);
        clInputCommentContainer = findViewById(R.id.cl_input_comment_container_articledetail);
        llBottomNavContainer = findViewById(R.id.ll_bottom_nav_container_articledetail);
        tvInputCommentHeader = findViewById(R.id.tv_header_input_comment_container);
        tvInputComment = findViewById(R.id.tv_write_comment_articledetail);
        llWriteComment = findViewById(R.id.ll_write_comment_articledetail);
        tvSubmitComment = findViewById(R.id.tv_submit_comment_articledetail);
        etComment = findViewById(R.id.et_input_comment_articledetail);
        nsvArticleDetail = findViewById(R.id.nsv_articledetail);

        /* Get Contents From Server */
        getContents(mBoardId);

        /* Toolbar*/
        setSupportActionBar(tbArticleDetail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_chevron_left_black);


        /* Scroll View Add on Layout Change Listener */
        nsvArticleDetail.addOnLayoutChangeListener(this);

        /* RefreshLayout */
        srlActicleDetail.setOnRefreshListener(this);
        srlBoardArticleDetail.setOnRefreshListener(this);

        /* RecyclerView */
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
        tvInputComment.setOnClickListener(this);
        llWriteComment.setOnClickListener(this);
        tvSubmitComment.setOnClickListener(this);

        /* Edit Text Change Watcher */
        etComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() == 0) {
                    tvSubmitComment.setEnabled(false);
                } else {
                    tvSubmitComment.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        /* Set View */
        if (sSharedPreferences.getBoolean(USER_LOGINNED, false))
            tvUserName.setText(sSharedPreferences.getString(USER_EMAIL, ""));
        else
            tvUserName.setText("로그인 해주세요.");
        tvArticleCommentCount.setText(String.valueOf(mCommentCount)); // 댓글수는 intent에서.
        tvArticleViewCount.setText(String.valueOf(mArticleViewCount)); // 조회수는 intent에서.
        tvArticleCategoty.setText(mCategoryType); // 카테고리 이름은 intent에서.
        clInputCommentContainer.setVisibility(View.GONE);
        tvInputCommentHeader.setVisibility(View.GONE);
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
        getContents(mBoardId);
        srlActicleDetail.setRefreshing(false);
        srlBoardArticleDetail.setRefreshing(false);
    }

    @Override
    public void onBackPressed() {
        if (INPUT_COMMENT_OPENED) {
            llBottomNavContainer.setVisibility(View.VISIBLE);
            clInputCommentContainer.setVisibility(View.GONE);
            tvInputCommentHeader.setVisibility(View.GONE);
            INPUT_COMMENT_OPENED = false;
            return;
        }
        super.onBackPressed();
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
            case R.id.ll_write_comment_articledetail:
            case R.id.tv_write_comment_articledetail:
                if (!INPUT_COMMENT_OPENED) {
                    INPUT_COMMENT_OPENED = true;
                    llBottomNavContainer.setVisibility(View.INVISIBLE);
                    clInputCommentContainer.setVisibility(View.VISIBLE);
                    tvInputCommentHeader.setVisibility(View.VISIBLE);
                }
                return;
            case R.id.tv_submit_comment_articledetail:
                postComment(mBoardId, etComment.getText().toString());
        }
        dlArticleDetail.closeDrawers();
    }

    private void getContents(int boardId) {
        showProgressDialog();
        final ArticleDetailService articleDetailService = new ArticleDetailService(this);
        articleDetailService.getArticleDetail(boardId);
    }

    private void postComment(int boardId, String comment) {
        showProgressDialog();
        final ArticleDetailService articleDetailService = new ArticleDetailService(this);
        articleDetailService.postComment(boardId, comment);
        etComment.setText("");
    }

    @Override
    public void validateSuccess(ArrayList<ArticleDetailResponse.Result> results) {
        hideProgressDialog();
        /* Set View */
        tvArticleAuthor.setText(results.get(0).getUserId()); // 글 작성자
        tvArticleTitle.setText(results.get(0).getTitle()); // 글 제목
        tvArticleContents.setText(results.get(0).getContents()); // 글 내용
        tvArticleCreatedAt.setText(results.get(0).getCreatedAt()); // 글 생성일

        String imgUri = results.get(0).getImgUri();
        if (imgUri != null && !imgUri.equals("")) {
            ivImgArticle.setVisibility(View.VISIBLE);
            Glide.with(this)
                    .load(imageStorageRef.child(results.get(0).getImgUri()))
                    .centerCrop()
                    .placeholder(R.drawable.iv_thumbnail_cafe_square)
                    .into(ivImgArticle);
        } else {
            ivImgArticle.setVisibility(View.GONE);
        }

        // Comment
        if (results.get(0).getCommentContents() != null && !results.get(0).getCommentContents().equals("")) {
            ArrayList<Comment> comments = new ArrayList<>();
            for (ArticleDetailResponse.Result result : results) {
                // comment adpater 호출.
                comments.add(new Comment(result.getCommentContents(), result.getCommentUser(), result.getCommentCreatedAt(), ""));
            }
            rvComments.setAdapter(new CommentListAdapter(comments, this));
        }
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showToast("내용을 불러오는데 실패하였습니다.");
    }

    @Override
    public void validateWriteCommentSuccess(String message) {
        hideProgressDialog();
        getContents(mBoardId);
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        switch (v.getId()) {
            case R.id.nsv_articledetail:
                if (bottom < oldBottom) {
                    nsvArticleDetail.fullScroll(View.FOCUS_DOWN);
                }
                break;
        }
    }
}
