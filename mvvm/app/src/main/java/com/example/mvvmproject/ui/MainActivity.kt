package com.example.mvvmproject.ui

import android.content.Intent
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.example.mvvmproject.*
import com.example.mvvmproject.base.activity.BaseActivity
import com.example.mvvmproject.ui.activity.LoginActivity
import com.example.mvvmproject.ui.fragment.HomeFragment
import com.example.mvvmproject.ui.fragment.KnowlegeFragment
import com.example.mvvmproject.ui.fragment.WXArticleFragment
import com.example.mvvmproject.utils.startActivityForClass
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class MainActivity : BaseActivity(), View.OnClickListener {


    private var mFragments: MutableList<Fragment> = mutableListOf()
    private var mLstPosition: Int = INIT_FIRST_FRAGMENT

    override fun initData() {
        initTitleBar(toolbar, resources.getString(R.string.home_pager))
        initFragment()
        switchFragment(INDEX_HOME_FRAGMENT)
        initDrawlerlayout()
    }

    override fun initParams(intent: Intent) {
    }

    override fun bindView(): View {
        return mLayoutInflator.inflate(R.layout.activity_main, null, false)
    }

    override fun initView(rootView: View) {
        bnv_main_tab.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
        bnv_main_tab.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.tab_main_pager -> {
                    showPager(R.string.home_pager, INDEX_HOME_FRAGMENT)
                }
                R.id.tab_knowledge_hierarchy -> {
                    showPager(R.string.knowledge_hierarchy, INDEX_KNOWLEDGE_FRAGMENT)
                }
                R.id.tab_wx_article->{
                    showPager(R.string.wx_article,INDEX_WX_ARTICLE_FRAGMENT)
                }
            }
            true
        }
        initLeftMenuView()
    }

    private fun initLeftMenuView(){
        ctl_menu_wanandroid.setOnClickListener(this)
        ctl_menu_collection.setOnClickListener(this)
        ctl_menu_setting.setOnClickListener(this)
        ctl_menu_about_us.setOnClickListener(this)
        ctl_left_top_container.setOnClickListener(this)
    }


    private fun showPager(title: Int, position: Int) {
        setTitle(resources.getString(title))
        switchFragment(position)
    }


    private fun initFragment() {
        mFragments = mutableListOf()
        mFragments.add(HomeFragment.newInstance(null))
        mFragments.add(KnowlegeFragment.newInstance(null))
        mFragments.add(WXArticleFragment.newInstance(null))
    }

    private fun switchFragment(position: Int) {
        if (position >= mFragments.size) {
            return
        }
        val ft = supportFragmentManager.beginTransaction()
        if (mLstPosition == INIT_FIRST_FRAGMENT) {
            mLstPosition = position
            val targetFragment = mFragments[mLstPosition]
            ft.add(R.id.fl_main_fragment_container, targetFragment)
            ft.show(targetFragment)
            ft.commit()
        } else {
            val lastFragment = mFragments[mLstPosition]
            val targetFragment = mFragments[position]
            mLstPosition = position
            ft.hide(lastFragment)
            if (!targetFragment.isAdded) {
                supportFragmentManager.beginTransaction().remove(lastFragment)
                    .add(R.id.fl_main_fragment_container, targetFragment)
                    .commitAllowingStateLoss()
            }
            ft.show(targetFragment)
            ft.commitAllowingStateLoss()
        }

    }

    private fun initDrawlerlayout() {
        val toggle = object : ActionBarDrawerToggle(
            this, dl_main_container, toolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ) {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                //获取mDrawerLayout中的第一个子布局，也就是布局中的RelativeLayout
                //获取抽屉的view
                val mContent = dl_main_container.getChildAt(0)
                val scale = 1 - slideOffset
                val endScale = 0.8f + scale * 0.2f
                val startScale = 1 - 0.3f * scale

                //设置左边菜单滑动后的占据屏幕大小
                drawerView.scaleX = startScale
                drawerView.scaleY = startScale
                //设置菜单透明度
                drawerView.alpha = 0.6f + 0.4f * (1 - scale)

                //设置内容界面水平和垂直方向偏转量
                //在滑动时内容界面的宽度为 屏幕宽度减去菜单界面所占宽度
                mContent.translationX = drawerView.measuredWidth * (1 - scale)
                //设置内容界面操作无效（比如有button就会点击无效）
                mContent.invalidate()
                //设置右边菜单滑动后的占据屏幕大小
                mContent.scaleX = endScale
                mContent.scaleY = endScale
            }

        }
        toggle.syncState()
        dl_main_container.addDrawerListener(toggle)
    }


    override fun onClick(view: View?) {
        when(view?.id){
            R.id.ctl_left_top_container->{
                startActivityForClass(LoginActivity::class.java)
            }
        }
    }

}