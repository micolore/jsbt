package com.kubrick.sbt.web.common.converter;

import com.kubrick.sbt.tools.date.DateTimeUtils;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

/**
 * @author k
 * @version 1.0.0
 * @ClassName DateConverter
 * @description: 字符串转日期
 * @date 2021/2/28 上午10:26
 */
public class DateConverter implements Converter<String, LocalDate> {

	@Override
	public LocalDate convert(String source) {
		if (source != null && !"".equals(source)) {
			return DateTimeUtils.parseLocalDate(source);
		}
		return null;
	}

}
