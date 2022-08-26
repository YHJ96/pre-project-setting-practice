package com.pre_38.pre_project.audit;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {
    //Test에서는 LocalDateTime.now() 사용, 실제구현에서는 필요없음
    //테스트에서는 Mock container를 사용하기 때문에 JPA컨테이너를 사용할 수 없기 때문에 @Created에너테이션이 의미가 없어짐


    @CreatedDate
    @Column(name = "CREATED_AT", updatable = false)
    private LocalDateTime date = LocalDateTime.now();


//    @LastModifiedDate
//    @Column(name = "LAST_MODIFIED_AT")
//    private LocalDateTime modifiedAt;
}
