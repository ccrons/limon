package com.limon.util;

import com.alibaba.fastjson.serializer.SerializerFeature;

public class JSONUtil {
	public static SerializerFeature[] features = {
			SerializerFeature.WriteMapNullValue, // 输出空置字段
			SerializerFeature.WriteNullStringAsEmpty, // list字段如果为null，输出为[]，而不是
		};
}
