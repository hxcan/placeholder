package com.stupidbeauty.hxlauncher;

import com.stupidbeauty.placeholder.R2;
import android.content.pm.ApplicationInfo;
import android.content.pm.LauncherApps;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.LauncherApps;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.stupidbeauty.threeupgrade.AnswerAvailableEvent;
import com.stupidbeauty.hxlauncher.application.HxLauncherApplication;
// import com.stupidbeauty.hxlauncher.bean.VoicePackageMapJsonItem;
// import com.stupidbeauty.hxlauncher.bean.VoicePackageUrlMapData;
import com.stupidbeauty.qtdocchinese.ArticleInfo;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * 应用程序信息适配器。
 */
public class ApplicationInformationAdapter extends RecyclerView.Adapter<ApplicationInformationAdapter.ClipViewHolder>
{
    private boolean builtinShortcutsVisible=false; //!<内置快捷方式是否可见。
    private HashMap<String, Integer> packageNameItemNamePositionMap=new HashMap<>(); //!<包名加类名的字符串与图标位置之间的映射。
    private HashMap<String, Integer> packageNamePositionMap=new HashMap<>(); //!<包名字符串与图标位置之间的映射。
    private static final String TAG="ApplicationInformationA"; //!<输出调试信息时使用的标记。
    private ShortcutInfoListManager shortcutInfoListManager; //!<快捷方式列表管理器。
    private final LauncherActivity context; //!<上下文。

    /**
     * 添加快捷方式。
     * @param shortcutInfo 要添加的快捷方式。
     */
    public void addShortcut(ShortcutInfo shortcutInfo)
    {
        Log.d(TAG, "addShortcut, this: " + this); //Debug.
        shortcutInfoListManager.addShortcut(shortcutInfo); //添加到列表中。
    } //private void addShortcut(ShortcutInfo shortcutInfo)

    /**
     * 设置文件信息列表。
     * @param articleInfoArrayList 要设置的文件信息列表。
     */
    public void setArticleInfoArrayList(ArrayList<ArticleInfo> articleInfoArrayList)
    {
        this.articleInfoArrayList = articleInfoArrayList; //记录列表。

        updateItemPositionMap(); //整体更新条目的位置映射
    } //public void setArticleInfoArrayList(ArrayList<ArticleInfo> articleInfoArrayList)

    /**
     * 切换内置快捷方式的可见性。
     * @param uid 是否可见。
     */
    public void toggleBuiltinShortcutsVisible(boolean uid)
    {
        builtinShortcutsVisible=uid; //记录。

        updateItemPositionMap(); //整体更新条目的位置映射
    } //public void toggleBuiltinShortcutsVisible(boolean uid)

    private ArrayList<ArticleInfo> articleInfoArrayList; //!<文章信息列表。
    private ArrayList<ArticleInfo> builtinShortcuts; //!<内置快捷方式列表。

    /**
     * 设置内置快捷方式列表
     * @param builtinShortcuts 内置快捷方式列表
     */
    public void setBuiltinShortcuts(ArrayList<ArticleInfo> builtinShortcuts)
    {

        this.builtinShortcuts = builtinShortcuts;

        updateItemPositionMap(); //整体更新条目的位置映射

    } //public void setBuiltinShortcuts(ArrayList<ArticleInfo> builtinShortcuts)

    public static class ClipViewHolder extends RecyclerView.ViewHolder
    {
//       public LauncherIconType iconType; //!<图标类型。

      /**
        * 显示应用信息
        */
      @OnLongClick({R2.id.homeNewsLayout, R2.id.launchRipple})
      public boolean  showApplicationInformation()
      {
        launcherActivity.showApplicationInformation(itemView, packageName, activityName); //显示应用程序信息活动

        return true;
      } //public void showApplicationInformation()

      @OnClick({R2.id.homeNewsLayout, R2.id.launchRipple})
      /**
        * 启动应用。
        */
      public void launchApplication()
      {
        launcherActivity.animateHeart(itemView.getX(), itemView.getY()); //显示点赞爱心动画
        launcherActivity.showApplicationInformation(itemView, packageName, activityName); //显示应用程序信息活动

//           if (iconType==ActivityIconType) //是活动。
//           {
//             launchAsActivity();
//           } //if (iconType==ActivityIconType) //是活动。
      } //public void launchApplication()

      /**
        * 以活动的方式启动．
        */
      private void launchAsActivity()
      {
        AnswerAvailableEvent answerAvailableEvent = new AnswerAvailableEvent(packageName, activityName); //创建事件消息对象．

        try //尝试启动活动，并且捕获可能的异常。
        {
          launcherActivity.launchApplication(launchIntent); //启动活动。
        }
        catch (ActivityNotFoundException exception)
        {
          exception.printStackTrace(); //报告错误。
        } //catch (ActivityNotFoundException exception)
      } //private void launchAsActivity()

        public String packageName; //!<应用包名。
        public String activityName; //!<具体的活动名字。
//        public Bus bus; //!<消息总线。
        public int clipId=0; //!<本视频对应的剪辑编号。
        public Intent launchIntent; //!<用于启动应用程序的意图。
        public ShortcutInfo shortcutInfo; //!<用于启动快捷方式的快捷方式信息对象。

        /**
         * 将文字加入队列中。
         * @param buttongetText 文字。
         */
        private void enqueueText(String buttongetText)
        {

            interactiveText=interactiveText+buttongetText; //加入到文字后面。
        } //private void enqueueText(String buttongetText)

        private String interactiveText=""; //!<手工校正的交互过程中的文字字符串。


        private void startCallTimer() {
            applyInteractiveText(); //应用交互过程中产生的文字。
        }

        /**
         * 应用交互过程中产生的文字。
         */
        private void applyInteractiveText()
        {
            mTextView.setText(interactiveText); //修改文字。

            interactiveText=""; //交互过程中累积的文字清空。
        } //private void applyInteractiveText()

        private final LauncherActivity launcherActivity; //!<上下文。

        @BindView(R2.id.rightTextoperationMethodactTitletextView2) TextView mTextView; //!<文字视图。

        @BindView(R2.id.applicationIconrightimageView2) ImageView applicationIconrightimageView2; //!<图标。

        public ClipViewHolder(View v, LauncherActivity context1)
        {
            super(v);

            ButterKnife.bind(this,v); //视图注入。

            launcherActivity =context1; //记录上下文。
        } //public ClipViewHolder(TextView v)
    }

    /**
     * 构造函数
     * @param context1 启动活动对象
     */
    public ApplicationInformationAdapter(LauncherActivity context1)
    {
        context=context1; //记录上下文。

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) //26之后才有钉住快捷方式请求对象。
        {
            shortcutInfoListManager=new ShortcutInfoListManager(); //创建管理器。
        } //if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) //26之后才有钉住快捷方式请求对象。

//         loadBuiltinVoiceShortcutMap(); //载入不可缓存的包名集合
    } //public ApplicationInformationAdapter(LauncherActivity context1)

    /**
     * 创建视图占位器。
     * @param parent 亲代视图。
     * @param viewType 视图类型。
     * @return 创建的占位器。
     */
    public ClipViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v= LayoutInflater.from(parent.getContext()).inflate(R2.layout.application_info_row,parent,false);

        ClipViewHolder clipViewHolder=new ClipViewHolder(v,context);
//        clipViewHolder.bus=bus;

        return clipViewHolder;
    } //public ClipViewHolder onCreateViewHolder(ViewGroup parent,int viewType)

    /**
     * 获取活动条目的位置。
     * @param packageItemInfopackageName 包名。
     * @param packageItemInfoname 活动类名。
     * @return 图标的位置编号。
     */
    public int getItemPosition(String packageItemInfopackageName, String packageItemInfoname)
    {
        Log.d(TAG, "getItemPosition, package name: " + packageItemInfopackageName + ", activity name: " + packageItemInfoname); //Debug.
        Log.d(TAG, "getItemPosition, packageNameItemNamePositionMap: " + packageNameItemNamePositionMap); //Debug.
        int result=0; //结果。

        if (packageItemInfoname!=null) //有类名。
        {
            Integer positionObject=packageNameItemNamePositionMap.get(packageItemInfopackageName+"/"+packageItemInfoname);

            if (positionObject!=null) //有结果
            {
                result=positionObject; //从映射中查找。

            } //if (positionObject!=null) //有结果

        } //if (packageItemInfoname!=null) //有类名。
        else //没有类名。
        {
          Integer positionInteger=packageNamePositionMap.get(packageItemInfopackageName); //从映射中查找。

          if (positionInteger!=null) //有条目。
          {
            result=positionInteger; //从映射中查找。
          } //if (positionInteger!=null) //有条目。
        } //else //没有类名。

        return result;
    } //public int getItemPosition(String packageItemInfopackageName, String packageItemInfoname)

    /**
     * 绑定视图占位器。
     * @param holder 占位器。
     * @param position 位置。
     */
    public void onBindViewHolder(ClipViewHolder holder, int position)
    {
        int formalShortcutAmount=0; //正式快捷方式个数。

        if (shortcutInfoListManager!=null) //有正式快捷方式。
        {
          formalShortcutAmount=shortcutInfoListManager.getShortcutAmount();
        } //if (shortcutInfoListManager!=null) //有正式快捷方式。

        if (position<articleInfoArrayList.size()) //活动。
        {
          bindViewHolderActivity(holder, position); //针对活动，绑定界面元素。
        } //if (position<articleInfoArrayList.size()) //活动。
        else if (position< (articleInfoArrayList.size() + formalShortcutAmount)) //快捷方式。
        {
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) //26之后才有钉住快捷方式的接口。
            {
                LauncherApps launcherApps=(LauncherApps)(context.getSystemService(Context.LAUNCHER_APPS_SERVICE));
                int shortcutInfoIndex=position-articleInfoArrayList.size(); //获取快捷方式的下标。

                ShortcutInfo shortcutInfo=shortcutInfoListManager.getShortcut(shortcutInfoIndex); //获取对应位置的快捷方式。

                String articleTitle=shortcutInfo.getShortLabel().toString(); //获取文章标题。

                Drawable applicationIcon=launcherApps.getShortcutIconDrawable(shortcutInfo, 0); //获取图标。

                holder.mTextView.setText(articleTitle); //设置新的文字内容。

                Glide.with(context).load("").placeholder(applicationIcon).into(holder.applicationIconrightimageView2); //显示图标。

                holder.packageName=shortcutInfo.getPackage(); //获取包名。
                holder.activityName=shortcutInfo.getActivity().getClassName(); //获取活动名字。

                holder.shortcutInfo=shortcutInfo; //记录快捷方式。
            } //if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) //26之后才有钉住快捷方式的接口。
        } //else //快捷方式。
        else //内置快捷方式。
        {
          bindViewHolderActivityBuiltinShortcuts(holder, position); //针对内置快捷方式的活动，绑定界面元素。
        } //else //内置快捷方式。
    } //public void onBindViewHolder(ClipViewHolder holder,int position)

    /**
     * 针对内置快捷方式的活动，绑定界面元素。
     * @param holder 视图容器。
     * @param wholePosition 整体的位置。
     */
    private void bindViewHolderActivityBuiltinShortcuts(ClipViewHolder holder, int wholePosition)
    {
      int formalShortcutAmount=0;

      if (shortcutInfoListManager!=null)
      {
        formalShortcutAmount=shortcutInfoListManager.getShortcutAmount();
      } //if (shortcutInfoListManager!=null)

      int position=wholePosition-articleInfoArrayList.size()-formalShortcutAmount; //计算出在内置快捷方式列表中的位置。
      ArticleInfo articleInfo=builtinShortcuts.get(position); //获取对应位置的文章。

      String articleTitle=articleInfo.getApplicationLabel().toString(); //获取文章标题。

      HxLauncherApplication application=HxLauncherApplication.getInstance(); //获取应用程序对象。
      Drawable applicationIcon=application.getApplicationIcon(articleInfo); // 获取图标。
      Intent applicationIntent=articleInfo.getLaunchIntent(); //获取启动意图。

      holder.mTextView.setText(articleTitle); //设置新的文字内容。

      Glide.with(context).load("").placeholder(applicationIcon).into(holder.applicationIconrightimageView2); //显示图标。

      holder.launchIntent=applicationIntent; //设置启动意图。
      holder.packageName=articleInfo.getPackageName(); //获取包名。
      holder.activityName=articleInfo.getActivityName(); //获取活动名字。
    } //private void bindViewHolderActivityBuiltinShortcuts(ClipViewHolder holder, int wholePosition)

    /**
     * 整体更新条目的位置映射
     */
    public void updateItemPositionMap()
    {
        int position=0; //记录的位置。

        if (articleInfoArrayList!=null) //指针有效。
        {
            for(ArticleInfo articleInfo: articleInfoArrayList) //一个个地绑定
            {
                packageNameItemNamePositionMap.put(articleInfo.getPackageName()+"/"+articleInfo.getActivityName(), position); //从映射中查找。

                position++;
            } //for(ArticleInfo articleInfo: articleInfoArrayList) //一个个地绑定


//            result=articleInfoArrayList.size();

        } //if (articleInfoArrayList!=null) //指针有效。

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N_MR1) //25之后才有快捷方式编号。
        {
            if (shortcutInfoListManager!=null) //快捷方式管理器有效。
            {
                int shortcutAmount=shortcutInfoListManager.getShortcutAmount(); //获取快捷方式个数。
                int shortcutCounter=0;

                for(shortcutCounter=0; shortcutCounter< shortcutAmount; shortcutCounter++) //一个个地映射
                {
                    ShortcutInfo shortcutInfo=shortcutInfoListManager.getShortcut(shortcutCounter);

                    packageNameItemNamePositionMap.put(shortcutInfo.getPackage()+"/"+shortcutInfo.getId(), position); //从映射中查找。


                    position++;

                } //for(shortcutCounter=0; shortcutCounter< shortcutAmount; shortcutCounter++) //一个个地映射


//            result=result+shortcutAmount; //加上快捷方式个数。
            } //if (shortcutInfoListManager!=null) //快捷方式管理器有效。

        } //if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N_MR1) //25之后才有快捷方式编号。


        if (builtinShortcuts!=null) //有内置快捷方式。
        {
            if (builtinShortcutsVisible) //内置快捷方式可见。
            {
                for(ArticleInfo articleInfo: builtinShortcuts) //一个个地绑定
                {
                    packageNameItemNamePositionMap.put(articleInfo.getPackageName()+"/"+articleInfo.getActivityName(), position); //从映射中查找。

                    position++;
                } //for(ArticleInfo articleInfo: articleInfoArrayList) //一个个地绑定
            } //if (builtinShortcutsVisible) //内置快捷方式可见。
        } //if (builtinShortcuts!=null) //有内置快捷方式。
    } //public void updateItemPositionMap()

    /**
     * 针对活动，绑定界面元素。
     * @param holder 视图容器。
     * @param position 位置。
     */
    private void bindViewHolderActivity(ClipViewHolder holder, int position)
    {
      ArticleInfo articleInfo=articleInfoArrayList.get(position); //获取对应位置的文章。

      String articleTitle=articleInfo.getApplicationLabel().toString(); //获取文章标题。

      HxLauncherApplication application=HxLauncherApplication.getInstance(); //获取应用程序对象。
      Drawable applicationIcon=application.getApplicationIcon(articleInfo); // 获取图标。
      Intent applicationIntent=articleInfo.getLaunchIntent(); //获取启动意图。

      holder.mTextView.setText(articleTitle); //设置新的文字内容。

      Glide.with(context).load("").placeholder(applicationIcon).into(holder.applicationIconrightimageView2); //显示图标。

      holder.launchIntent=applicationIntent; //设置启动意图。
      holder.packageName=articleInfo.getPackageName(); //获取包名。
      holder.activityName=articleInfo.getActivityName(); //获取活动名字。
    } //private void bindViewHolderActivity(ClipViewHolder holder, int position)

    /**
     * 获取条目个数。
     * @return 条目个数。
     */
    public int getItemCount()
    {
        int result=0; //个数。

        if (articleInfoArrayList!=null) //指针有效。
        {
            result=articleInfoArrayList.size();

        } //if (articleInfoArrayList!=null) //指针有效。

        if (shortcutInfoListManager!=null) //快捷方式管理器有效。
        {
            int shortcutAmount=shortcutInfoListManager.getShortcutAmount(); //获取快捷方式个数。

            result=result+shortcutAmount; //加上快捷方式个数。
        } //if (shortcutInfoListManager!=null) //快捷方式管理器有效。

        if (builtinShortcuts!=null) //有内置快捷方式。
        {
            if (builtinShortcutsVisible) //内置快捷方式可见。
            {
                result=result+builtinShortcuts.size(); //加上内置快捷方式的数量。

            } //if (builtinShortcutsVisible) //内置快捷方式可见。
        } //if (builtinShortcuts!=null) //有内置快捷方式。

        return result;
    } //public int getItemCount()
} //public class SettingTabAboutActivity extends Activity implements OnClickListener
