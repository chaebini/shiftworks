package org.shiftworks.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostVO {
	private int post_id;
	private int b_id;
	private String emp_id;
	private String dept_id;
	private String post_name;
	private String post_content;
	private int post_regno;
	private String post_regdate;
	private String post_updatedate;
	private char post_fix;
	private String post_receivedept;
	private String name;
	private List<FileVO> fileList;

}
