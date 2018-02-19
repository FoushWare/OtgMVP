package com.example.foush.otgmvp.ui.Dataset;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foush.otgmvp.R;
import com.example.foush.otgmvp.data.ApiHelper;
import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.data.ServiceGenerator;
import com.example.foush.otgmvp.otgMvp;
import com.example.foush.otgmvp.ui.Base.BaseActivity;
import com.example.foush.otgmvp.ui.Main.MainActivity;
import com.example.foush.otgmvp.ui.Profile.ProfileActivity;
import com.example.foush.otgmvp.utils.BitmapUtils;
import com.example.foush.otgmvp.utils.CommonUtils;
import com.glidebitmappool.GlideBitmapFactory;
import com.glidebitmappool.GlideBitmapPool;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

import org.zeroturnaround.zip.ZipUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by foush on 09/02/18.
 */

public class DataSetActivity extends BaseActivity implements DataSetMvpView {
    ProgressDialog progress;
    @BindView(R.id.image_view)
    ImageView imageView;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.pic1)
    Button pic1;
    @BindView(R.id.pic2)
    Button pic2;
    @BindView(R.id.pic3)
    Button pic3;

    ProgressDialog dialog;

    private static final int flagPic1 = 1;
    private static final int flagPic2 = 2;
    private static final int flagPic3 = 3;
    private static final String FILE_PROVIDER_AUTHORITY = "com.example.android.fileproviderv4";
    private static final String TAG = MainActivity.class.getSimpleName();
    private static Uri photoURI;

    private String mTempPhotoPath;

    private Bitmap mResultsBitmap;
    File photoFile = null;
    private Bitmap theBitmap = null;
    String file_path;
    public Bitmap myBitmap;
    public Canvas tempCanvas;
    public Paint myRectPaint;
    //save the uri of the 3 photos taken
    private Bitmap tempBitmap;
    private static List<File> filesList = new ArrayList<File>();
    private static int count = 0;

    float x1 = 0;
    float x2 = 0;
    float y1 = 0;
    float y2 = 0;
    DataManager mDataManager;
    DataSetPresenter mDataSetPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataset);

        mDataManager = ((otgMvp) getApplication()).getmDataManger();
        mDataSetPresenter = new DataSetPresenter(mDataManager);
        mDataSetPresenter.onAttach(this);

        initView();


    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        /**
         * Glide Bitmap Pool is a memory management library for reusing the bitmap memory.
         * As it reuses bitmap memory , so no more GC calling again and again ,
         * hence smooth running application. It uses inBitmap while decoding the bitmap
         * on the supported android versions. All the version use-cases has been handled to optimize
         * it better.
         */
        GlideBitmapPool.initialize(10 * 1024 * 1024); // 10mb max memory size
        /*init the progress bar for the upload zip file part*/
    }

    @OnClick({R.id.pic1, R.id.pic2, R.id.pic3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pic1:
                if (CommonUtils.checkCameraPermission(DataSetActivity.this) == true) {
                    // Launch the camera if the permission exists
                    launchCamera(flagPic1);
                }

                break;
            case R.id.pic2:
                if (CommonUtils.checkCameraPermission(DataSetActivity.this) == true) {
                    // Launch the camera if the permission exists
                    launchCamera(flagPic2);
                }
                break;
            case R.id.pic3:
                if (CommonUtils.checkCameraPermission(DataSetActivity.this) == true) {
                    // Launch the camera if the permission exists
                    launchCamera(flagPic3);
                }
                break;

            default:
                Toast.makeText(this, "fuck you", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * Creates a temporary image file and captures a picture to store in it.
     */
    @Override
    public void launchCamera(int flag) {


        // Create the capture image intent
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the temporary File where the photo should go

            try {
                photoFile = BitmapUtils.createTempImageFile(this);
            } catch (IOException ex) {
                // Error occurred while creating the File
                ex.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {

                // Get the path of the temporary file
                mTempPhotoPath = photoFile.getAbsolutePath();
                Log.d(TAG, "launchCamera: photo uri uri uri uri uri uri is  " + mTempPhotoPath);


                // Get the content URI for the image file
                photoURI = FileProvider.getUriForFile(this,
                        FILE_PROVIDER_AUTHORITY,
                        photoFile);

                // Add the URI so the camera can store the image
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

                // Launch the camera activity
                startActivityForResult(takePictureIntent, flag);
            }
        }

    }

    @Override
    public void prepareFaceDetector() {
        //Load the Image with it's uri
        myBitmap = GlideBitmapFactory.decodeFile(mTempPhotoPath);
        //imageView.setImageBitmap(myBitmap);

        //create a paint object for drawing with
        myRectPaint = new Paint();
        myRectPaint.setStrokeWidth(5);
        myRectPaint.setColor(Color.RED);
        myRectPaint.setStyle(Paint.Style.STROKE);


        //create a canvas object for drawing on
        tempBitmap = Bitmap.createBitmap(myBitmap.getWidth(), myBitmap.getHeight(), Bitmap.Config.RGB_565);
        tempCanvas = new Canvas(tempBitmap);
        tempCanvas.drawBitmap(myBitmap, 0, 0, null);


    }

    @Override
    public void processImage(int flag) {
/**
 * Method for processing the captured image and setting it to the ImageView.
 */
        prepareFaceDetector();

        //create the face detector
        FaceDetector faceDetector = new
                FaceDetector.Builder(getApplicationContext()).setTrackingEnabled(false)
                .build();
        if (!faceDetector.isOperational()) {
            new AlertDialog.Builder(this).setMessage("Could not set up the face detector!").show();
            return;

        }
        //Detect faces
        //Draw Rectangles on the faces
        //Detect the Faces
        Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
        SparseArray<Face> faces = faceDetector.detect(frame);

        //No faces in the picture
        if (faces.size() == 0) {
            Toast.makeText(this, "No faces detected Please take the picture again", Toast.LENGTH_LONG).show();
            noFace(flag);

        } else {
            /**if there is a face in the image*/

            //Draw Rectangles on the Faces
            for (int i = 0; i < faces.size(); i++) {
                Face thisFace = faces.valueAt(i);
                x1 = thisFace.getPosition().x;
                y1 = thisFace.getPosition().y;
                x2 = x1 + thisFace.getWidth();
                y2 = y1 + thisFace.getHeight();

                tempCanvas.drawRoundRect(new RectF(x1, y1, x2, y2), 2, 2, myRectPaint);

            }
            //crop the image to get only the image of the user
            Bitmap croppedBitmap = Bitmap.createBitmap(tempBitmap, (int) x1, (int) y1, (int) x2 - (int) x1, (int) y2 - (int) y1);

            imageView.setImageDrawable(new BitmapDrawable(getResources(), croppedBitmap));
            //Presenter role

            mDataSetPresenter.savingAndZipping(mTempPhotoPath, croppedBitmap, flag, count);


        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // If the image capture activity was called and was successful
        if (requestCode == flagPic1) {
            //using the first button (1) to pick first photo

            if (resultCode == RESULT_OK) {
                /** process the image and set it to the imageView*/

                processImage(flagPic1);


            } else {
            }


        } else if (requestCode == flagPic2) {
            //using the first button (2) to pick first photo
            if (resultCode == RESULT_OK) {
                /** process the image and set it to the imageView*/
                processImage(flagPic2);

            } else {
            }


        } else if (requestCode == flagPic3) {
            //using the first button (2) to pick first photo

            if (resultCode == RESULT_OK) {
                /** process the image and set it to the imageView*/
                processImage(flagPic3);

            } else {
            }


        } else {/**do nothing :) */}


    }

    @Override
    public void buttonsGone(int flag) {
        switch (flag) {

            case flagPic1:
                pic1.setVisibility(View.GONE);
                ++count;

                break;
            case flagPic2:
                pic2.setVisibility(View.GONE);
                ++count;

                break;
            case flagPic3:
                pic3.setVisibility(View.GONE);
                ++count;
                break;

        }
    }

    @Override
    public void noFace(int flag) {
        switch (flag) {

            case flagPic1:
                launchCamera(flagPic1);
                break;
            case flagPic2:
                launchCamera(flagPic2);

                break;
            case flagPic3:
                launchCamera(flagPic3);

                break;

        }
    }

    @Override
    public void saveImageAndPath(String mTempPhotoPath, Bitmap croppedBitmap) {
        /**save the image and get the images path*/

        // Delete the temporary image file
        BitmapUtils.deleteImageFile(this, mTempPhotoPath);
        // Save the image
        String imagPath = BitmapUtils.saveImage(this, croppedBitmap);
        Log.d(TAG, "processAndSetImage: image path is " + imagPath);
    }

    @Override
    public void userZipPhotos() {
//zip all the photos in the file
        ZipUtil.pack(new File(String.valueOf(BitmapUtils.storageDir)), new File(String.valueOf(BitmapUtils.storageDir) + ".zip"));
        // ZipUtil.unexplode(new File( BitmapUtils.storageDir + ".zip"));
        // write function to send the ziped file to the server [moustafa]
        Log.d(TAG, "uploading zip file: welcome to the server  zip path is " + BitmapUtils.storageDir + ".zip");
       uploadZipFile(BitmapUtils.storageDir + ".zip");


    }


    @Override
    public void uploadZipFile(final String zipFile) {
        Log.d(TAG, "uploadZipFile: can't upload the zip file");

        //redirect to my third_party script base Url
        ServiceGenerator.changeApiBaseUrl("http://ahmedfouad.esy.es/computerVision/");


        // create upload service client
        ApiHelper apiHelper = ServiceGenerator.createService(ApiHelper.class);

        //get the actual file by the  url
        File file = new File(zipFile);

        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse("application/zip"),
                        file
                );


        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("zipFile", file.getName(), requestFile);


        // add another part within the multipart request
        String descriptionString = "hello, this is description speaking";
        RequestBody description =
                RequestBody.create(
                        MultipartBody.FORM, descriptionString);

        progress();

        // finally, execute the request

        Call<ResponseBody> call = apiHelper.upload(description, body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                dialog.dismiss();
                Intent intent=new Intent(DataSetActivity.this, ProfileActivity.class);
                startActivity(intent);
                Log.v("Upload", "success");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
            }
        });


    }

    private String getMimeType(String path) {

        String extension = MimeTypeMap.getFileExtensionFromUrl(path);

        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
    }



    public void progress(){

        dialog = new ProgressDialog(DataSetActivity.this); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Loading. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}
