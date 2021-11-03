package com.mathiasuy.automotora.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.slf4j.MDC;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.mathiasuy.automotora.utils.Constants;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class Auditable<U> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@CreationTimestamp
	@Column(name = "created", nullable = false, updatable = false)
	private LocalDateTime created;
	
	@LastModifiedDate
	@Column(name = "lastUpdate", nullable = false)
	private LocalDateTime lastUpdate;
	
	@CreatedBy
	@Column(name = "createdBy", nullable = false, updatable = false)
	private U createdBy;
	
	@LastModifiedBy
	@Column(name = "lastModifiedBy", nullable = false)
	private U lastModifiedBy;

	@Column(name = "correlationId")
	private String correlationId;

	protected Auditable() {
		this.setCorrelationId(MDC.get(Constants.REQUEST_ID_HEADER));
	}
	
}

