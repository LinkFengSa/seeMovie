package com.seeMovie.common.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.seeMovie.pojo.SystemLogVo;
import com.seeMovie.service.MovieService;
import com.seeMovie.service.SystemLogService;

/**
 * 
 * @author	 	mym
 * @date   		2018年7月21日下午1:36:45
 * @describe 	定时更新影片信息  例如影片类型、影片分类等
 */
@Component
public class UpdateMovieInfoTimer {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	MovieService movieService;
	@Autowired
	SystemLogService systemLogService;
	/**
	 * 
	 * @author  	mym
	 * @date        2018年7月21日下午3:58:35
	 * @return      void
	 * @describe    更新影片类型(电影/电视剧)
	 */
	@Scheduled(cron="0 0/25 3 * * ?") //每天凌晨3点开始每隔25分钟执行一次 0 0/25 3 * * ?
	public void UpdateMovieCategoryInfoTimer(){ 
		SystemLogVo systemLogVo = new SystemLogVo();
		systemLogVo.setLogId(UUID.randomUUID().toString().replaceAll("-", ""));
		systemLogVo.setLogName("更新影片类型(电影/电视剧)  UpdateMovieCategoryInfoTimer()");
		String startDate = sdf.format(new Date());
		systemLogVo.setLogContent("定时器开始执行时间为"+startDate);
		try {
			movieService.UpdateMovieCategoryInfoTimer();
			String endDate = sdf.format(new Date());
			systemLogVo.setLogContent(systemLogVo.getLogContent()+",定时器执行结束时间为"+endDate
					+"。此次定时器持续时间为"+(sdf.parse(endDate).getTime()-sdf.parse(startDate).getTime())/1000+"秒！");
			systemLogVo.setLogLevel("1");
			systemLogVo.setLogCreateDate(new Date());
			systemLogService.insertSystemLog(systemLogVo);
		} catch (Exception e) {
			String endDate = sdf.format(new Date());
			try {
				systemLogVo.setLogContent("定时器执行异常		"+systemLogVo.getLogContent()+",定时器执行结束时间为"+endDate
						+"。此次定时器持续时间为"+(sdf.parse(endDate).getTime()-sdf.parse(startDate).getTime())/1000+"秒！"+e.getMessage().substring(0, 500));
				systemLogVo.setLogLevel("1");
				systemLogVo.setLogCreateDate(new Date());
				systemLogService.insertSystemLog(systemLogVo);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @author		mym
	 * @date        2018年8月24日下午3:32:21
	 * @describe    更新影片连接
	 * @return      void
	 */
	@Scheduled(cron="0 0/30 3 * * ?")//每天凌晨3点开始每隔30分钟执行一次 0 0/30 3 * * ?
	public void UpdateMovieImgUrlInfoTimer(){ 
		SystemLogVo systemLogVo = new SystemLogVo();
		systemLogVo.setLogId(UUID.randomUUID().toString().replaceAll("-", ""));
		systemLogVo.setLogName("更新影片连接  UpdateMovieImgUrlInfoTimer()");
		String startDate = sdf.format(new Date());
		systemLogVo.setLogContent("定时器开始执行时间为"+startDate);
		try {
			movieService.UpdateMovieImgUrlInfoTimer();
			String endDate = sdf.format(new Date());
			systemLogVo.setLogContent(systemLogVo.getLogContent()+",定时器执行结束时间为"+endDate
					+"。此次定时器持续时间为"+(sdf.parse(endDate).getTime()-sdf.parse(startDate).getTime())/1000+"秒！");
			systemLogVo.setLogLevel("1");
			systemLogVo.setLogCreateDate(new Date());
			systemLogService.insertSystemLog(systemLogVo);
		} catch (Exception e) {
			String endDate = sdf.format(new Date());
			try {
				systemLogVo.setLogContent("定时器执行异常		"+systemLogVo.getLogContent()+",定时器执行结束时间为"+endDate
						+"。此次定时器持续时间为"+(sdf.parse(endDate).getTime()-sdf.parse(startDate).getTime())/1000+"秒！"+e.getMessage().substring(0, 500));
				systemLogVo.setLogLevel("1");
				systemLogVo.setLogCreateDate(new Date());
				systemLogService.insertSystemLog(systemLogVo);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
