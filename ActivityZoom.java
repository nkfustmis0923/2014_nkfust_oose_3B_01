package com.example.user.hello;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.user.hello.SQL;

import static android.provider.BaseColumns._ID;
import static com.example.user.hello.DbConstants.ACC;
import static com.example.user.hello.DbConstants.ADR;

import static com.example.user.hello.DbConstants.NAME;
import static com.example.user.hello.DbConstants.PWD;
import static com.example.user.hello.DbConstants.TABLE_NAME;
import static com.example.user.hello.DbConstants.TEL;


public class ActivityZoom extends Activity implements View.OnClickListener {
    private LinearLayout mGallery;
    private int[] mImgIds;
    private LayoutInflater mInflater;
    private Animator mCurrentAnimator;
    private int mShortAnimationDuration;
    Button btnEdit;
    int count=0;
    private DBHelper dbhelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_activity_zoom);


        ImageButton button=(ImageButton)findViewById(R.id.shopping);
         final Button info1=(Button)findViewById(R.id.info1);
        final Button info2=(Button)findViewById(R.id.info2);
        final Button info3=(Button)findViewById(R.id.info3);

        info3.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         info3();
                                     }
                                 }
        );

        info1.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         info1();
                                     }
                                 }
        );

        info2.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         info2();
                                     }
                                 }
        );
        button.setOnClickListener(new View.OnClickListener(){
                                      @Override public void onClick( View v){
                                    if(count>0)
                                    {
                                        Order();
                                    }
                                         else
                                          ShowAlertDialog();

                                      }
                                  }
        );






    final View thumb1View=findViewById(R.id.image6);
        thumb1View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImageFromThumb(thumb1View, R.drawable.coat1);
            }
        });
        final View thumb1View2=findViewById(R.id.image2);
        thumb1View2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImageFromThumb(thumb1View2, R.drawable.coat2);
            }
        });



        mShortAnimationDuration = getResources().getInteger(
                android.R.integer.config_shortAnimTime);
        mInflater = LayoutInflater.from(this);

    }

    private void info1()
    {
        final AlertDialog.Builder MyAlertDialog = new AlertDialog.Builder(this);
        MyAlertDialog.setTitle("灰色拉鍊針織領大衣");
        MyAlertDialog.setMessage("價錢:NT$6990元\n\n模特身高:185 厘米\n\n成分:50% 羊毛, 26% 聚醯胺纖維, 15% 亞克力纖維, 9% 其他纖維" +
                "\n");
        AlertDialog.Builder myAlertDialog = MyAlertDialog;
        final AlertDialog dialog = myAlertDialog.create();

        dialog.show();
    }
    private void info2()
    {
        final AlertDialog.Builder MyAlertDialog = new AlertDialog.Builder(this);
        MyAlertDialog.setTitle("針織領灰色大衣");
        MyAlertDialog.setMessage("價錢:NT$4990元\n\n模特身高:185 厘米\n\n成分:50% 羊毛, 26% 聚醯胺纖維, 15% 亞克力纖維, 9% 其他纖維" +
                "\n");
        AlertDialog.Builder myAlertDialog = MyAlertDialog;
        final AlertDialog dialog = myAlertDialog.create();

        dialog.show();
    }
    private void info3()
    {
        final AlertDialog.Builder MyAlertDialog = new AlertDialog.Builder(this);
        MyAlertDialog.setTitle("尼龍大衣");
        MyAlertDialog.setMessage("價錢:NT$4990元\n\n模特身高:185 厘米\n\n成分:50% 羊毛, 26% 聚醯胺纖維, 15% 亞克力纖維, 9% 其他纖維" +
                "\n");
        AlertDialog.Builder myAlertDialog = MyAlertDialog;
        final AlertDialog dialog = myAlertDialog.create();

        dialog.show();
    }

    private void ShowAlertDialog()
    {


              final AlertDialog.Builder MyAlertDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(ActivityZoom.this);

       final View alert_view = inflater.inflate(R.layout.login_view,null);

        MyAlertDialog.setTitle("您尚未登錄!");

        MyAlertDialog.setView(alert_view);


         AlertDialog.Builder myAlertDialog = MyAlertDialog;

        myAlertDialog.setPositiveButton(getString(R.string.alert_button_R),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        //如果未輸入提示使用者輸入

                        launchIntent();
                    }
                });

        myAlertDialog.setNegativeButton(getString(R.string.alert_button_L), new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface arg0, int arg1) {



                   final EditText acc=(EditText)alert_view.findViewById(R.id.userName);
                 final EditText pwd=(EditText)alert_view.findViewById(R.id.pass);

                String account=acc.getText().toString().trim();
                String pass=pwd.getText().toString().trim();

                if(account.length()!=0&&pass.length()!=0){

                            access();
                            count++;
                }

                else
               Custom();

            }
        });
        final AlertDialog dialog = myAlertDialog.create();

        dialog.show();

    }
    private  void launchIntent()
    {
        Intent it=new Intent(ActivityZoom.this,SQL.class);
        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(it);
    }
    private void Order()
    {
        Intent order=new Intent(ActivityZoom.this,ORDER_SQL.class);
        order.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(order);
    }
    public void access()
    {

        final AlertDialog.Builder MyAlertDialog3 = new AlertDialog.Builder(this);
        final AlertDialog.Builder myAlertDialog3 = MyAlertDialog3;
        myAlertDialog3.setMessage("恭喜您登錄成功");
        myAlertDialog3.setPositiveButton(getString(R.string.ok_label),
                new DialogInterface.OnClickListener()
        {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        //如果未輸入提示使用者輸入
                        Order();
                    }
    });
        final AlertDialog dialog3 = myAlertDialog3.create();

        dialog3.show();
    }
   private void Custom()
   {

           final AlertDialog.Builder MyAlertDialog2 = new AlertDialog.Builder(this);
           final AlertDialog.Builder myAlertDialog2 = MyAlertDialog2;

           MyAlertDialog2.setMessage("您的帳號或密碼尚未輸入!\n請重新再輸入一次");
           myAlertDialog2.setPositiveButton(getString(R.string.ok_label),
                   new DialogInterface.OnClickListener(){
                       @Override
                       public void onClick(DialogInterface arg0, int arg1) {
                           //如果未輸入提示使用者輸入

                       }
                   });
       final AlertDialog dialog2 = myAlertDialog2.create();

       dialog2.show();
   }





    @Override
    public void onClick(View v) {

    }

 /*   private void initData()
    {
        mImgIds = new int[] { R.drawable.dasom2, R.drawable.soyou2, R.drawable.hyorin6,
                R.drawable.bora2, R.drawable.hyorin};
    }

    private void initView()
    {
        mGallery = (LinearLayout) findViewById(R.id.id_gallery);

        for (int i = 0; i < mImgIds.length; i++)
        {

            View view = mInflater.inflate(R.layout.fragment_activity_zoom,
                    mGallery, false);

            ImageView img = (ImageView) view
                    .findViewById(R.id.id_index_gallery_item_image);
            img.setImageResource(mImgIds[i]);

            mGallery.addView(view);
        }


    } */

    private void zoomImageFromThumb(final View thumbView, int imageResId) {
        //如果一个动画正在进行过程中，那么就要立即取消之前的动画并进行这一个。
        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }

        // 载入一个高分辨率的所谓 "已放大" 的图片.
        final ImageView expandedImageView = (ImageView) findViewById(
                R.id.image);
        expandedImageView.setImageResource(imageResId);

        // 为放大的图片计算开始动画和结束动画的矩形边界
        // 这个步骤牵扯到了大量的数学计算，YEAH!!坑爹的数学!!
        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        // 动画开始的边界是缩略图对全局可见的矩形，最终的边界是外部包裹的布局可见矩形。
        // 这里还设置了包裹视图的偏移量为原点的边界,因为这是原点为定位的动画属性(X, Y)。
        thumbView.getGlobalVisibleRect(startBounds);
        findViewById(R.id.container).getGlobalVisibleRect(finalBounds, globalOffset);
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        // 调整开始边界要和使用了“centerCrop”技术的最终边界保持相同的纵横比。
        // 这可以在动画过程中防止不希望出现的拉伸现象。还计算了开始大小的缩放系数
        // (结束大小的系数则一直为1.0)
        float startScale;
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {
            // 水平扩展开始边界
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            // 竖直扩展开始边界
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        // 隐藏缩略图并显示放大后的View。当动画开始，将在缩略图的位置定位放大的视图
        thumbView.setAlpha(0f);
        expandedImageView.setVisibility(View.VISIBLE);

        // 设置锚点，以放大后的View左上角坐标为准来准备 SCALE_X 和 SCALE_Y 变换
        // (默认为View的中心)
        expandedImageView.setPivotX(0f);
        expandedImageView.setPivotY(0f);

        // 构建并并行化运行4个平移动画和缩放属性(X, Y, SCALE_X, and SCALE_Y)
        AnimatorSet set = new AnimatorSet();
        set
                .play(ObjectAnimator.ofFloat(expandedImageView, View.X,
                        startBounds.left, finalBounds.left))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.Y,
                        startBounds.top, finalBounds.top))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X,
                        startScale, 1f)).with(ObjectAnimator.ofFloat(expandedImageView,
                View.SCALE_Y, startScale, 1f));
        set.setDuration(mShortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mCurrentAnimator = null;
            }
        });
        set.start();
        mCurrentAnimator = set;

        // 点击放大后的图片，应该是缩放回原来的边界并显示缩略图
        // 而不是显示扩大的图
        final float startScaleFinal = startScale;
        expandedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentAnimator != null) {
                    mCurrentAnimator.cancel();
                }

                // 开始并行动画这四个位置/大小属性，直到归至原始值。
                AnimatorSet set = new AnimatorSet();
                set.play(ObjectAnimator
                        .ofFloat(expandedImageView, View.X, startBounds.left))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.Y, startBounds.top))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_Y, startScaleFinal));
                set.setDuration(mShortAnimationDuration);
                set.setInterpolator(new DecelerateInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }
                });
                set.start();
                mCurrentAnimator = set;
            }
        });
    }
}
