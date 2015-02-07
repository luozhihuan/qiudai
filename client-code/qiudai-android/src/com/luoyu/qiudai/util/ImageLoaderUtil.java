package com.luoyu.qiudai.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Handler;

import com.luoyu.qiudai_android.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

public class ImageLoaderUtil {

	/**
	 * 获取一个ImageLoader的实例并进行相应配置
	 * 
	 * @param context
	 * @return ImageLoader的实例
	 */
	public static ImageLoader initImaeLoaderAndConfigured(Context context) {
		// 获取imageLoader的实例
		ImageLoader imageLoader = ImageLoader.getInstance();
		// 初始化一个默认配置的imageloader
		// ImageLoaderConfiguration.createDefault(context)
		// 返回一个默认配置的配置对象ImageLoaderConfiguration
		// init方法就是将该配置进行初始化
		imageLoader.init(ImageLoaderConfiguration.createDefault(context));
		return imageLoader;
	}

	/**
	 * 构建DisplayImageOptions实例
	 * 
	 * @return 构建好的DisplayImageOptions实例
	 */
	public static DisplayImageOptions BuilderDisplayImageOptions() {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
		// 是否设置为圆角，弧度为多少
				.bitmapConfig(Bitmap.Config.RGB_565)
				.cacheInMemory(true)// 设置下载的图片缓存在内存中
				.cacheOnDisc(true)// 设置下载的图片缓存在SD卡中
				.build();// 构建完成
		return options;
	}

	public static void stepsForStudy(Context context) {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).memoryCacheExtraOptions(480, 800)
				// default = device screen dimensions
				.discCacheExtraOptions(480, 800, CompressFormat.JPEG, 75, null).taskExecutor(null)
				// 此处不能传入空，为了不报错暂时保留了错误的写法
				.taskExecutorForCachedImages(null)
				// 此处不能传入空，为了不报错暂时保留了错误的写法
				.threadPoolSize(3)
				// default
				.threadPriority(Thread.NORM_PRIORITY - 1)
				// default
				.tasksProcessingOrder(QueueProcessingType.FIFO)
				// default
				.denyCacheImageMultipleSizesInMemory().memoryCache(new LruMemoryCache(2 * 1024 * 1024)).memoryCacheSize(2 * 1024 * 1024)
				.memoryCacheSizePercentage(13) // default
				.discCache(new UnlimitedDiscCache(null)) // default
															// //此处不能传入空，为了不报错暂时保留了错误的写法
				.discCacheSize(50 * 1024 * 1024).discCacheFileCount(100).discCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
				.imageDownloader(new BaseImageDownloader(context)) // default
				.imageDecoder(new BaseImageDecoder(false)) // default
				.defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
				.writeDebugLogs().build();

		/********************************************/
		DisplayImageOptions options = new DisplayImageOptions.Builder()
		// .showImageOnLoading(R.drawable.messi) //设置图片在下载期间显示的图片
				.showImageForEmptyUri(R.drawable.messi) // 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(R.drawable.messi) // 设置图片加载/解码过程中错误时候显示的图片
				.resetViewBeforeLoading(false) // default设置图片在下载前是否重置，复位
				.delayBeforeLoading(1000)// 设置图片下载前的延迟
				.cacheInMemory(false) // default设置下载的图片是否缓存在内存中
				.cacheOnDisc(false) // default设置下载的图片是否缓存在SD卡中
				.preProcessor(null)// 置图片加入缓存前，对bitmap进行设置
				.postProcessor(null)// 设置显示前的图片，显示后这个图片一直保留在缓存中
				.extraForDownloader(null)// 设置额外的内容给ImageDownloader
				// .considerExifParams(false) // default
				.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)// 设置图片以如何的编码方式显示
				.bitmapConfig(Bitmap.Config.ARGB_8888) // default设置图片的解码类型
				.decodingOptions(null)// 设置图片的解码配置
				.displayer(new SimpleBitmapDisplayer()) // default
				.handler(new Handler()) // default
				.build();
	}
}
