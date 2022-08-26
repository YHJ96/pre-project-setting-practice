package com.pre_38.pre_project.member.entity;

import com.pre_38.pre_project.audit.Auditable;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false)
    private String avatar;

    @Column(nullable = false)
    private String email;
}
