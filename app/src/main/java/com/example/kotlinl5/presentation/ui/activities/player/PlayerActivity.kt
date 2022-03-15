package com.example.kotlinl5.presentation.ui.activities.player

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.net.Uri
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.WindowManager
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.example.kotlinl5.BuildConfig.BASE_YOUTUBE
import com.example.kotlinl5.core.ui.base.BaseActivity
import com.example.kotlinl5.domain.models.Item
import com.example.kotlinl5.databinding.ActivityPlayerBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.google.android.exoplayer2.source.MergingMediaSource

@SuppressLint("StaticFieldLeak")
class PlayerActivity : BaseActivity<PlayerViewModel, ActivityPlayerBinding>() {

    private lateinit var videoId: String
    private lateinit var exoPlayer: ExoPlayer

    private lateinit var youtubeLink: String

    private lateinit var videoUrl: String
    private lateinit var audioUrl: String

    private lateinit var videoSource: MediaSource
    private lateinit var audioSource: MediaSource

    override val viewModel: PlayerViewModel by viewModel()

    override fun initView() {
        super.initView()
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        videoId = intent.getStringExtra("videoId").toString()
        youtubeLink = BASE_YOUTUBE + videoId
        extractYoutubeLink()
    }

    override fun initListener() {
        super.initListener()
        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.btnDownload.setOnClickListener {
            createAlertDialog()
        }
    }

    private fun initPlayer() {
        createMediaSource(videoUrl, audioUrl)
        exoPlayer = ExoPlayer.Builder(this).build()
        binding.exoplayerView.player = exoPlayer
        exoPlayer.setMediaSource(MergingMediaSource(videoSource, audioSource))
        exoPlayer.playWhenReady = true
        exoPlayer.prepare()
    }

    private fun extractYoutubeLink() {
        object : YouTubeExtractor(this) {
            override fun onExtractionComplete(
                ytFiles: SparseArray<YtFile>?,
                videoMeta: VideoMeta?
            ) {
                if (ytFiles != null) {
                    val itag = 133
                    val audioTag = 140

                    videoUrl = ytFiles[itag].url
                    audioUrl = ytFiles[audioTag].url
                    initPlayer()
                }
            }
        }.extract(youtubeLink)
    }

    private fun createMediaSource(videoUrl: String, audioUrl: String) {
        val videoDataSource: DataSource.Factory = DefaultDataSourceFactory(
            this,
            Util.getUserAgent(this, applicationInfo.name)
        )

        videoSource = ProgressiveMediaSource.Factory(videoDataSource).createMediaSource(
            MediaItem.fromUri(Uri.parse(videoUrl))
        )
        val audioDataSource: DataSource.Factory = DefaultDataSourceFactory(
            this,
            Util.getUserAgent(this, applicationInfo.name)
        )

        audioSource = ProgressiveMediaSource.Factory(audioDataSource).createMediaSource(
            MediaItem.fromUri(Uri.parse(audioUrl))
        )

    }

    @SuppressLint("InflateParams")
    private fun createAlertDialog() {
        val builder = AlertDialog.Builder(this)
        val items = arrayOf("480p", "720p", "1080p")

        builder.setTitle("asd")
            .setSingleChoiceItems(items,0) { _, which ->
                when (which) {
                    0 -> downloadVideo(135)
                    1 -> downloadVideo(136)
                    2 -> downloadVideo(137)
                }
            }
            .show()
    }

    private fun downloadVideo(itag: Int) {
        object : YouTubeExtractor(this) {
            override fun onExtractionComplete(
                ytFiles: SparseArray<YtFile>?,
                videoMeta: VideoMeta?
            ) {
                if (ytFiles != null) {
                    val audioTag = 140
                    videoUrl = ytFiles[itag].url
                    audioUrl = ytFiles[audioTag].url
                    //TODO downloader
                }
            }
        }.extract(youtubeLink)
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.getVideo(videoId).observe(this){
            onBindViews(it.data?.items)
        }
    }

    private fun onBindViews(items: List<Item>?) {
        binding.tvTitle.text = items?.get(0)?.snippet?.title
        binding.tvDescription.text = items?.get(0)?.snippet?.description
    }

    override fun onPause() {
        super.onPause()
        exoPlayer.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        exoPlayer.stop()
    }

    override fun inflateVB(inflater: LayoutInflater): ActivityPlayerBinding {
        return ActivityPlayerBinding.inflate(layoutInflater)
    }
}
