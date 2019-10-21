package com.gd.travel.common.baseEntity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * 请求返回结果
 * @author GD
 * @date 2019/9/29
 * @param <T>
 */
@Data
public class ResultVO<T> {

	/** 请求状态码 */
	@ApiModelProperty(value="请求状态码，200-正确，其它-错误", dataType="int")
	private Integer status;

	/** 请求状态描述 */
	@ApiModelProperty(value="请求状态描述", dataType="message")
	private String message;

	/** 响应数据，可以为空 */
	@ApiModelProperty(value="响应数据：成功时返回需要的数据，失败时返回详细原因或为null", dataType="Object")
	private T data;
	
	// 函数
	/**
	 * 请求成功，返回ResultVO，但data为空
	 * @return
	 */
	public static <T> ResultVO<T> getSuccess() {
		ResultVO<T> vo = new ResultVO<>();
		vo.setStatus(HttpStatus.OK.value());
		vo.setMessage(HttpStatus.OK.getReasonPhrase());
		return vo;
	}

	/**
	 * 请求成功，返回ResultVO，有data字段
	 * @param t
	 * @return
	 */
	public static <T> ResultVO<T> getSuccess(T t) {
		ResultVO<T> vo = new ResultVO<>();
		vo.setStatus(HttpStatus.OK.value());
		vo.setMessage(HttpStatus.OK.getReasonPhrase());
		vo.setData(t);
		return vo;
	}

	/**
	 * 请求参数错误
	 * @param detail 错误详情
	 * @return
	 */
	public static <T> ResultVO<T> getParamsError(String detail) {
		ResultVO<T> vo = new ResultVO<>();
		vo.setStatus(HttpStatus.BAD_REQUEST.value());
		vo.setMessage(detail);
		return vo;
	}

	/**
	 * 服务器错误
	 * @param detail 错误详情
	 * @return
	 */
	public static <T> ResultVO<T> getServerError(String detail) {
		ResultVO<T> vo = new ResultVO<>();
		vo.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		vo.setMessage(detail);
		return vo;
	}

	/**
	 * 第三方服务器返回错误
	 * @param t 第三方服务器返回的数据
	 * @return
	 */
	public static <T> ResultVO<T> getThirdServerError(T t) {
		ResultVO<T> vo = new ResultVO<>();
		vo.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		vo.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		vo.setData(t);
		return vo;
	}
	
}
