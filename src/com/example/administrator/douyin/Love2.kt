package com.example.administrator.douyin

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.content.Context
import android.os.SystemClock
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.RelativeLayout
import java.util.*
import com.stupidbeauty.hxlauncher.R

//e: /Data/SoftwareDevelop/hxlauncher/HxLauncher/app/src/main/kotlin/com/example/administrator/douyin/Love2.kt: (17, 25): Packages cannot be imported

/**
 * information：仿抖音点赞功能
 */
class Love2(context: Context, attrs: android.util.AttributeSet) : RelativeLayout(context, attrs) 
{
    var mContext: Context? = context
    //动画中随机❤的旋转角度
    var num = floatArrayOf(-35f, -25f, 0f, 25f, 35f)

    //用来判断是否是连续的点击事件
    private val mHits = LongArray(3)

    override fun onTouchEvent(event: MotionEvent?): Boolean 
    {
      System.arraycopy(mHits, 1, mHits, 0, mHits.size - 1)
      mHits[mHits.size - 1] = SystemClock.uptimeMillis()

      var x =event?.x!!
      var y = event?.y!!

      {
          animateHeart(x, y)
      }

      return super.onTouchEvent(event)

    } // override fun onTouchEvent(event: MotionEvent?): Boolean 

    public fun animateHeart(x: Float, y: Float) {
        var iv: ImageView = ImageView(mContext)

        var lp: LayoutParams = LayoutParams(300, 300)
        lp.leftMargin = (x - 150F).toInt()
        lp.topMargin = (y - 300F).toInt()
//        lp.leftMargin = (event?.x!! - 150F).toInt()
//        lp.topMargin = (event?.y!! - 300F).toInt()
        iv.setImageDrawable(resources.getDrawable(R.mipmap.icon_home_like_after))
        iv.layoutParams = lp

        //把IV添加到父布局当中
        addView(iv)

        //设置控件的动画
        var animatorSet: AnimatorSet = AnimatorSet()
        animatorSet.play(
                scaleAni(iv, "scaleX", 2f, 0.9f, 100, 0))
                //缩放动画，Y轴2倍缩放至0.9倍
                .with(scaleAni(iv, "scaleY", 2f, 0.9f, 100, 0))
                .with(rotation(iv, 0, 0, num[Random().nextInt(4)]))
                //渐变透明动画，透明度从0-1
                .with(alphaAni(iv, 0F, 1F, 100, 0))
                //缩放动画，X轴0.9倍缩小至
                .with(scaleAni(iv, "scaleX", 0.9f, 1F, 50, 150))
                //缩放动画，Y轴0.9倍缩放至
                .with(scaleAni(iv, "scaleY", 0.9f, 1F, 50, 150))
                .with(translationY(iv, 0F, -600F, 800, 400))
                //透明动画，从1-0
                .with(alphaAni(iv, 1F, 0F, 300, 400))
                //缩放动画，X轴1至3倍
                .with(scaleAni(iv, "scaleX", 1F, 3f, 700, 400))
                .with(scaleAni(iv, "scaleY", 1F, 3f, 700, 400))

        animatorSet.start()
        //设置动画结束监听
        animatorSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)

                removeViewInLayout(iv)
            }
        })
    }


    fun scaleAni(view: View, propertyName: String, from: Float, to: Float, time: Long, delayTime: Long): ObjectAnimator 
    {
      val ani: ObjectAnimator = ObjectAnimator.ofFloat(view, propertyName, from, to)
      ani.interpolator = LinearInterpolator()
      ani.startDelay = delayTime
      ani.duration = time
      return ani
    } // fun scaleAni(view: View, propertyName: String, from: Float, to: Float, time: Long, delayTime: Long): ObjectAnimator 

    fun translationX(view: View, from: Float, to: Float, time: Long, delayTime: Long): ObjectAnimator 
    {
      val ani: ObjectAnimator = ObjectAnimator.ofFloat(view, "translationX", from, to)
      ani.interpolator = LinearInterpolator()
      ani.startDelay = delayTime
      ani.duration = time
      return ani
    } // fun translationX(view: View, from: Float, to: Float, time: Long, delayTime: Long): ObjectAnimator 

    fun translationY(view: View, from: Float, to: Float, time: Long, delayTime: Long): ObjectAnimator 
    {
      val ani: ObjectAnimator = ObjectAnimator.ofFloat(view, "translationY", from, to)
      ani.interpolator = LinearInterpolator()
      ani.startDelay = delayTime
      ani.duration = time
      return ani
    } // fun translationY(view: View, from: Float, to: Float, time: Long, delayTime: Long): ObjectAnimator 

    fun alphaAni(view: View, from: Float, to: Float, time: Long, delayTime: Long): ObjectAnimator 
    {
        val ani = ObjectAnimator.ofFloat(view, "alpha", from, to)
        ani.interpolator = LinearInterpolator()
        ani.startDelay = delayTime
        ani.duration = time
        return ani
    } // fun alphaAni(view: View, from: Float, to: Float, time: Long, delayTime: Long): ObjectAnimator 

    fun rotation(view: View, time: Long, delayTime: Long, vararg values: Float): ObjectAnimator 
    {
      val ani = ObjectAnimator.ofFloat(view, "rotation", *values)
      ani.duration = time
      ani.startDelay = delayTime
      ani.interpolator = TimeInterpolator { input -> input }
      return ani
    } // fun rotation(view: View, time: Long, delayTime: Long, vararg values: Float): ObjectAnimator 

}
