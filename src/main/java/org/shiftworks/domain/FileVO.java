package org.shiftworks.domain;

import lombok.Data;

@Data
public class FileVO {
	private String uuid;
	private int work_id;
	private String file_name;
	private String file_src;
	
}