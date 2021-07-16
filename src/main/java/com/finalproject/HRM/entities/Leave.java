package com.finalproject.HRM.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "leaves")
@ToString
@Builder
@Getter
@Setter
public class Leave {
	@Id
	private String id;
	private String leaveName;
	private Integer days;

}
