package com.its.library.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter@Setter
@Table(name = "love")
public class LoveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "memberId")
    private Long memberId;
    @Column(name = "debutId")
    private Long debutId;
    @Column(name = "love")
    private int love;

}