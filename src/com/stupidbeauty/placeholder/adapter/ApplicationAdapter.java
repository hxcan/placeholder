package com.stupidbeauty.placeholder.adapter;

import android.net.Uri;
import com.stupidbeauty.hxlauncher.manager.ActiveUserReportManager;
import android.os.UserHandle;
import android.app.Activity;
import android.content.Context;
import com.stupidbeauty.victoriafresh.VFile;
import static com.stupidbeauty.hxlauncher.datastore.LauncherIconType.ActivityIconType;
import static com.stupidbeauty.hxlauncher.datastore.LauncherIconType.ShortcutIconType;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import butterknife.BindView;
import butterknife.ButterKnife;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.LauncherApps;
import java.util.HashSet;
import java.util.Locale;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.recyclerview.widget.RecyclerView;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.stupidbeauty.qtdocchinese.ArticleInfo;
import com.stupidbeauty.placeholder.R2;
import com.stupidbeauty.placeholder.R;
import java.util.ArrayList;
import java.util.List;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
// import com.huiti.msclearnfootball.AnswerAvailableEvent;
import com.stupidbeauty.hxlauncher.application.HxLauncherApplication;
import com.stupidbeauty.hxlauncher.bean.ApplicationNameInternationalizationData;

public class ApplicationAdapter extends RecyclerView.Adapter<ApplicationAdapter.ApplicationViewHolder> {

    private List<ArticleInfo> mApplications = new ArrayList<>();

    public void setApplications(List<ArticleInfo> applications) {
        mApplications.clear();
        mApplications.addAll(applications);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ApplicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_application, parent, false);
        return new ApplicationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ApplicationViewHolder holder, int position) {
        ArticleInfo app = mApplications.get(position);
        holder.bind(app);
    }

    @Override
    public int getItemCount() {
        return mApplications.size();
    }

    public class ApplicationViewHolder extends RecyclerView.ViewHolder 
    {
      private HashSet<String> iconNoCachePackageNameSet=new HashSet<>(); //!<不应当缓存其图标的软件包名集合

        @BindView(R2.id.app_icon)
        ImageView appIcon;
        @BindView(R2.id.app_name)
        TextView appName;
        @BindView(R2.id.app_size)
        TextView appSize;
        @BindView(R2.id.uninstall_button)
        TextView uninstallButton;

        public ApplicationViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    /**
     * 尝试载入内置的应用程序图标。
     * @param articleInfo 应用程序信息。
     * @return 内置的应用程序图标。
     */
    private  Drawable getBuiltinApplicationIcon(ArticleInfo articleInfo)
    {
      Drawable result=null; //结果。

      String packageName=articleInfo.getPackageName(); //获取包名。
      String qrcFileName=packageName; //文件名。
      String fullQrcFileName=":/ApplicationIcon/"+qrcFileName; //构造完整的qrc文件名。

      HxLauncherApplication application=HxLauncherApplication.getInstance(); //获取应用程序对象。
      VFile qrcHtmlFile=new VFile(application, fullQrcFileName); //qrc网页文件。

      if (qrcHtmlFile.exists()) //文件存在。
      {
          byte[] photoBytes= qrcHtmlFile.getFileContent(); //将照片文件内容全部读取。

          ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(photoBytes);


          result= Drawable.createFromStream(byteArrayInputStream, "JPEG"); //从文件中解码。
      } //if (qrcHtmlFile.exists()) //文件存在。

      return result;
    } //private  Drawable getBuiltinApplicationIcon(ArticleInfo articleInfo)

    /**
     * 获取图标。
     * @param articleInfo 应用程序信息。
     * @return 应用程序的启动图标。
     */
    private Drawable getApplicationIcon(ArticleInfo articleInfo)
    {
      Drawable result; //结果。

      result=getBuiltinApplicationIcon(articleInfo); //尝试载入内置的应用程序图标。

      if (result==null) //没有内置的应用程序图标。
      {
        result=getSystemProvidedApplicationIcon(articleInfo); //获取由系统提供的应用程序图标。
      } //if (result==null) //没有内置的应用程序图标。

      return result;
    } //private Drawable getApplicationIcon(ArticleInfo articleInfo)

    /**
     * 获取由系统提供的应用程序图标。
     * @param articleInfo 应用程序信息。
     * @return 由系统提供的应用程序图标。
     */
    private Drawable getSystemProvidedApplicationIcon(ArticleInfo articleInfo)
    {
        Drawable result; //结果。

        String packageName=articleInfo.getPackageName(); //获取应用程序包名。

        String activityName=articleInfo.getActivityName(); //获取活动名字


        HxLauncherApplication application=HxLauncherApplication.getInstance(); //获取应用程序对象。

        HashMap<String,Drawable> launchIconMap=application.getLaunchIconMap(); //获取启动图标缓存。

        result=launchIconMap.get(packageName + "/" + activityName); //获取缓存绘图对象。

        if (result==null) //未缓存。
        {
            PackageManager packageManager=application.getPackageManager(); //获取软件包管理器。

            try //读取图标内容
            {
                PackageInfo packageInfo=packageManager.getPackageInfo(packageName,0); //获取应用程序信息。

                ApplicationInfo applicationInfo=packageInfo.applicationInfo; //获取应用程序信息。

//                result=packageManager.getApplicationIcon(applicationInfo); //获取图标。


                ComponentName componentName=new ComponentName(packageName, activityName); //创建部件名字对象

                result=packageManager.getActivityIcon(componentName); //获取活动的图标

                if (iconNoCachePackageNameSet.contains(packageName)) //不应当缓存
                {
                } //if (iconNoCachePackageNameSet.contains(packageName)) //不应当缓存
                else //可以缓存
                {
                    launchIconMap.put(packageName + "/" + activityName, result); //加入缓存。
                } //else //可以缓存
            } //try //读取图标内容
            catch (PackageManager.NameNotFoundException e) //未找到该应用程序包。
            {
                e.printStackTrace(); //报告错误。
            } //catch (PackageManager.NameNotFoundException e) //未找到该应用程序包。
            catch (OutOfMemoryError outOfMemoryError)
            {
                outOfMemoryError.printStackTrace(); //报告错误。
            } //catch (OutOfMemoryError outOfMemoryError)
        } //if (result==null) //未缓存。

        return result;
    } //private Drawable getSystemProvidedApplicationIcon(ArticleInfo articleInfo)

    private String formatFileSize(long sizeInBytes) {
    if (sizeInBytes < 1024) {
        return String.format("%d B", sizeInBytes);
    } else if (sizeInBytes < 1024 * 1024) {
        return String.format("%.2f KB", sizeInBytes / 1024.0);
    } else if (sizeInBytes < 1024 * 1024 * 1024) {
        return String.format("%.2f MB", sizeInBytes / (1024.0 * 1024));
    } else {
        return String.format("%.2f GB", sizeInBytes / (1024.0 * 1024 * 1024));
    }
}


        public void bind(ArticleInfo app) 
        {
          HxLauncherApplication hxlauncherApplication=HxLauncherApplication.getInstance(); // 获取应用对象。
          Drawable applicationIcon=getApplicationIcon(app); //获取图标。
          Glide.with(hxlauncherApplication).load("").placeholder(applicationIcon).into(appIcon); // 显示图标。

          // appIcon.setImageResource(R.drawable.ic_launcher_background); // Placeholder
          appName.setText(app.getApplicationLabel());
          
          
          // 使用这个函数来格式化应用的大小
          long appSizeInBytes = app.getSize(); // 假设 getSize() 返回的是字节
          String formattedSize = formatFileSize(appSizeInBytes);
          appSize.setText(formattedSize);
          // appSize.setText(String.format("%d MB", app.getSize()));
        }

        @OnClick(R2.id.uninstall_button)
        public void onUninstallButtonClick() 
        {
          ArticleInfo app = mApplications.get(getAdapterPosition());
          // Uninstall logic goes here
            
          // 获取应用的包名，这通常是用于标识应用的唯一 ID
          String packageName = app.getPackageName() ; // 假设 ArticleInfo 有一个名为 packageName 的字段

          // 创建一个 Intent 并设置 ACTION_DELETE 动作以及应用的包名
          Intent intent = new Intent(Intent.ACTION_DELETE);
          intent.setData(Uri.parse("package:" + packageName));
          intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

          // 启动卸载流程
          HxLauncherApplication application=HxLauncherApplication.getInstance(); //获取应用程序对象。
          application.startActivity(intent);
        }
    }
}
