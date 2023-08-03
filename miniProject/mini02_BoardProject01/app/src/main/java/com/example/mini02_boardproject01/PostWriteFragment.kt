package com.example.mini02_boardproject01


import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.provider.MediaStore
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import com.example.mini02_boardproject01.databinding.FragmentPostWriteBinding
import com.example.mini02_boardproject01.repository.PostRepository
import com.example.mini02_boardproject01.repository.RepositoryPostList.Companion.addData
import com.example.mini02_boardproject01.vm.TestData
import com.example.mini02_boardproject01.vm.ViewModelPostList
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.concurrent.thread

class PostWriteFragment : Fragment() {

    lateinit var fragmentPostWriteBinding: FragmentPostWriteBinding
    lateinit var mainActivity: MainActivity
    private lateinit var viewModel: ViewModelPostList

    // 게시판 종류
    var boardType = 0

    var idx = 0L

    // 업로드할 이미지의 Uri
    var uploadUri: Uri? = null

    // 카메라 액티비티를 실행하기 위한 런처
    lateinit var cameraLauncher: ActivityResultLauncher<Intent>

    // 앨범 액티비티를 실행하기 위한 런처
    lateinit var albumLauncher: ActivityResultLauncher<Intent>

    val fileName = "image/test.jpg"

    var userIdx=0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentPostWriteBinding = FragmentPostWriteBinding.inflate(inflater)
        mainActivity = activity as MainActivity


        // 카메라 설정
        cameraLauncher = cameraSetting(fragmentPostWriteBinding.imageView)

        albumLauncher = albumSetting(fragmentPostWriteBinding.imageView)

        viewModel = ViewModelProvider(this)[ViewModelPostList::class.java]

        fragmentPostWriteBinding.run {
            toolbarPostWrite.run {
                title = "글작성"
                inflateMenu(R.menu.post_write_menu)
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.POST_WRITE_FRAGMENT)
                }

                setOnMenuItemClickListener {
                    when (it.itemId) {

                        //카메라 사용하기
                        R.id.photoCamera -> {
                            val newIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

                            // 사진이 저장될 파일 이름
                            val fileName = "/temp_upload.jpg"

                            //경로
                            val filePath = mainActivity.getExternalFilesDir(null).toString()

                            // 경로 + 파일이름
                            val picPath = "${filePath}/${fileName}"
                            val file = File(picPath)
                            // 사진이 저장될 경로를 관리할 Uri 객체를 생성한다.
                            uploadUri = FileProvider.getUriForFile(
                                mainActivity,
                                "com.example.mini02_boardproject01.file_provider",
                                file
                            )
                            newIntent.putExtra(MediaStore.EXTRA_OUTPUT, uploadUri)
                            cameraLauncher.launch(newIntent)

                        }

                        // 앨범에서 사진 가져오기
                        R.id.photoLibrary -> {
                            val newIntent = Intent(
                                Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                            )
                            newIntent.setType("image/*")
                            val mimeType = arrayOf("image/*")
                            newIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeType)
                            albumLauncher.launch(newIntent)
                        }
                        // 작성완료
                        R.id.done -> {

                            val subject = textInputEditTextPostWriteTitle.text.toString()
                            val text = textInputEditTextPostWriteDetail.text.toString()


                            if (subject.isEmpty()) {
                                val builder = MaterialAlertDialogBuilder(mainActivity)
                                builder.setTitle("제목 입력 오류")
                                builder.setMessage("제목을 입력해주세요")
                                builder.setPositiveButton("확인") { _: DialogInterface, _: Int ->
                                    mainActivity.showSoftInput(textInputEditTextPostWriteTitle)
                                }
                                builder.show()
                                return@setOnMenuItemClickListener true
                            }

                            if (boardType == 0) {
                                val builder = MaterialAlertDialogBuilder(mainActivity)
                                builder.setTitle("게시판 종류 선택 오류")
                                builder.setMessage("게시판 종류를 선택해주세요")
                                builder.setPositiveButton("확인", null)
                                builder.show()
                                return@setOnMenuItemClickListener true
                            }

                            if (text.isEmpty()) {
                                val builder = MaterialAlertDialogBuilder(mainActivity)
                                builder.setTitle("내용 입력 오류")
                                builder.setMessage("내용를 입력해주세요")
                                builder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                                    mainActivity.showSoftInput(textInputEditTextPostWriteDetail)
                                }
                                builder.show()
                                return@setOnMenuItemClickListener true
                            }

                            val userName = textInputEditTextPostWriteTitle.text
                            val userDetail = textInputEditTextPostWriteDetail.text


                            val t1 = TestData(idx, userName.toString(), userDetail.toString())
                            idx++
                            addData(requireContext(), t1)

                            val userIdError =
                                if (TextUtils.isEmpty(userName)) "이름을 입력해주세요." else null
                            val userPasswordError =
                                if (TextUtils.isEmpty(userDetail)) "내용을 입력해주세요." else null

                            textInputLayoutPostWriteTitle.error = userIdError
                            textInputLayoutPostWriteDetail.error = userPasswordError

                            val database = FirebaseDatabase.getInstance()
                            // 게시글 인덱스 번호
                            val postIdxRef = database.getReference("PostIdx")
                            PostRepository.getPostIdx {
                                var postIdx = it.result.value as Long
                                // 게시글 인덱스를 증가한다.
                                postIdx++

                                // 게시글을 저장한다.
                                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                                val writeDate = sdf.format(Date(System.currentTimeMillis()))

                                val fileName = if (uploadUri == null) {
                                    "None"
                                } else {
                                    "image/img_${System.currentTimeMillis()}.jpg"
                                }

                                val postDataClass = PostDataClass(
                                    postIdx, boardType.toLong(), subject,
                                    text, writeDate, fileName, mainActivity.loginUserClass.userIdx
                                )
                                val postDataRef = database.getReference("PostData")
                                PostRepository.addPostInfo(postDataClass) {
                                    // 게시글 인덱스번호 저장

                                    PostRepository.setPostIdx(postIdx) {

                                        // 글 번호를 번들에 담아준다.
                                        val newBundle = Bundle()
                                        newBundle.putLong("readPostIdx", postIdx)
                                        newBundle.putLong("userIdx", userIdx)

                                        // 이미지 업로드
                                        if (uploadUri != null) {
                                            PostRepository.uploadImage(uploadUri!!, fileName){
                                                    Snackbar.make(
                                                        fragmentPostWriteBinding.root,
                                                        "저장되었습니다",
                                                        Snackbar.LENGTH_SHORT
                                                    ).show()

                                                    if (userIdError == null && userPasswordError == null) {
                                                        mainActivity.replaceFragment(MainActivity.POST_READ_FRAGMENT,true,newBundle)
                                                    }
                                                }
                                        } else {
                                            Snackbar.make(
                                                fragmentPostWriteBinding.root,
                                                "저장되었습니다",
                                                Snackbar.LENGTH_SHORT
                                            ).show()

                                            if (userIdError == null && userPasswordError == null) {
                                                mainActivity.replaceFragment(MainActivity.POST_READ_FRAGMENT,true,newBundle)
                                            }
                                        }
                                    }

                                }

                            }
                        }

                    }
                    false
                }

            }

            textInputEditTextPostWriteTitle.requestFocus()

            thread {
                // 500ms 쉬게 한다.
                // onCreate 메서드의 수행이 끝날 때 까지 대기한다.
                SystemClock.sleep(500)
                val imm =
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(textInputEditTextPostWriteTitle, 0)
            }


            // 게시판 종류 버튼
            button.setOnClickListener {
                val builder = MaterialAlertDialogBuilder(mainActivity)
                builder.setTitle("게시판 종류")
                builder.setItems(mainActivity.boardTypeList) { dialogInterface: DialogInterface, i: Int ->
                    boardType = i + 1
                    button.text = mainActivity.boardTypeList[i]
                }
                builder.setNegativeButton("취소", null)
                builder.show()
            }
        }
        return fragmentPostWriteBinding.root
    }

    // 카메라 관련 설정

    fun cameraSetting(previewImageView: ImageView): ActivityResultLauncher<Intent> {
        // 사진 촬영을 위한 런처
        val cameraContract = ActivityResultContracts.StartActivityForResult()
        val cameraLauuncher = registerForActivityResult(cameraContract) {
            if (it?.resultCode == AppCompatActivity.RESULT_OK) {
                //Uri를 이용해 이미지에 접근하여 Bitmap 객체로 생성한다.
                val bitmap = BitmapFactory.decodeFile(uploadUri?.path)


                // 이미지 크기 조정
                val ratio = 1024.0 / bitmap.width
                val targetHeight = (bitmap.height * ratio).toInt()
                val bitmap2 = Bitmap.createScaledBitmap(bitmap, 1024, targetHeight, false)

                // 회전 각도값을 가져온다.
                val degree = getDegree(uploadUri!!)

                // 회전 이미지를 생성한다.
                val matrix = Matrix()
                matrix.postRotate(degree.toFloat())
                val bitmap3 =
                    Bitmap.createBitmap(bitmap2, 0, 0, bitmap2.width, bitmap2.height, matrix, false)
                previewImageView.setImageBitmap(bitmap3)
            }

        }
        return cameraLauuncher
    }

    // 이미지 파일에 기록되어 있는 회전 정보를 가져온다.
    fun getDegree(uri: Uri): Int {

        var exifInterface: ExifInterface? = null

        // 사진 파일로 부터 tag 정보를 관리하는 객체를 추출한다.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val photoUri = MediaStore.setRequireOriginal(uri)
            // 스트림을 추출한다.
            val inputStream = mainActivity.contentResolver.openInputStream(photoUri)
            // ExifInterface 정보를 읽엉돈다.
            exifInterface = ExifInterface(inputStream!!)
        } else {
            exifInterface = ExifInterface(uri.path!!)
        }
        var degree = 0
        if (exifInterface != null) {
            // 각도 값을 가져온다.

            val orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1)

            when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> degree = 90
                ExifInterface.ORIENTATION_ROTATE_180 -> degree = 180
                ExifInterface.ORIENTATION_ROTATE_270 -> degree = 270
            }
        }

        return degree
    }

    // 앨범 관련 설정
    fun albumSetting(previewImageView: ImageView): ActivityResultLauncher<Intent> {
        val albumContract = ActivityResultContracts.StartActivityForResult()
        val albumLauncher = registerForActivityResult(albumContract) {
            if (it.resultCode == AppCompatActivity.RESULT_OK) {
                // 선택한 이미지에 접근할 수 있는 Uri 객체를 추출한다.
                if (it.data?.data != null) {
                    uploadUri = it.data?.data
                    // 안드로이드 10 (Q) 이상이라면..
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        // 이미지를 생성할 수 있는 디코더를 생성한다.
                        val source =
                            ImageDecoder.createSource(mainActivity.contentResolver, uploadUri!!)
                        // Bitmap 객체를 생성한다.
                        val bitmap = ImageDecoder.decodeBitmap(source)

                        previewImageView.setImageBitmap(bitmap)
                    } else {
                        // 컨텐츠 프로바이더를 통해 이미지 데이터 정보를 가져온다.
                        val cursor =
                            mainActivity.contentResolver.query(uploadUri!!, null, null, null, null)
                        if (cursor != null) {
                            cursor.moveToNext()

                            // 이미지의 경로를 가져온다.
                            val idx = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
                            val source = cursor.getString(idx)

                            // 이미지를 생성하여 보여준다.
                            val bitmap = BitmapFactory.decodeFile(source)
                            previewImageView.setImageBitmap(bitmap)
                        }
                    }
                }
            }
        }
        return albumLauncher
    }
}

