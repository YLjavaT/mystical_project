package com.its.library.dto;

import com.its.library.entity.BaseEntity;
import com.its.library.entity.DebutEpisodeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DebutEpisodeDTO{

    private Long id;
    private Long categoryId;
    private Long memberId;
    private String memberName;
    private String feat;
    private String debutTitle;
    private String introduce;
    private String debutContents;
    private int debutHits;
    private MultipartFile debutImg;
    private String debutImgName;
    private LocalDateTime createdTime;
    private int love;


    public DebutEpisodeDTO(Long id) {
    }

    public DebutEpisodeDTO(Long id, Long categoryId, String memberName, String feat, String debutTitle, String introduce, int debutHits, String debutImgName) {
        this.id = id;
        this.categoryId = categoryId;
        this.memberName = memberName;
        this.feat = feat;
        this.debutTitle = debutTitle;
        this.introduce = introduce;
        this.debutHits = debutHits;
        this.debutImgName = debutImgName;
    }

    public static DebutEpisodeDTO toDTO(DebutEpisodeEntity debutEpisodeEntity) {
        DebutEpisodeDTO debutEpisodeDTO = new DebutEpisodeDTO();
        debutEpisodeDTO.setId(debutEpisodeEntity.getId());
        debutEpisodeDTO.setDebutTitle(debutEpisodeEntity.getDebutTitle());
        debutEpisodeDTO.setCreatedTime(debutEpisodeEntity.getCreatedDateTime());
        debutEpisodeDTO.setDebutImgName(debutEpisodeEntity.getDebutImgName());
        debutEpisodeDTO.setDebutContents(debutEpisodeEntity.getDebutContents());
        debutEpisodeDTO.setMemberName(debutEpisodeEntity.getMemberName());
        debutEpisodeDTO.setMemberId(debutEpisodeEntity.getMemberEntity().getId());
        debutEpisodeDTO.setFeat(debutEpisodeEntity.getFeat());
        debutEpisodeDTO.setCategoryId(debutEpisodeEntity.getDebutCategoryEntity().getId());
        debutEpisodeDTO.setIntroduce(debutEpisodeEntity.getIntroduce());
        debutEpisodeDTO.setDebutHits(debutEpisodeEntity.getDebutHits());
        debutEpisodeDTO.setLove(debutEpisodeEntity.getLove());
        return debutEpisodeDTO;
    }
}
