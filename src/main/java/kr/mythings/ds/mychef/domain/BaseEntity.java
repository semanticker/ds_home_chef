package kr.mythings.ds.mychef.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
@MappedSuperclass
public class BaseEntity {

    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime enterDate;
    @Column(updatable = false)
    @CreatedBy
    private String enterBy;
    @Column(insertable = false)
    @LastModifiedDate
    private LocalDateTime modifyDate;
    @Column(insertable = false)
    @LastModifiedBy
    private String modifyBy;

}
