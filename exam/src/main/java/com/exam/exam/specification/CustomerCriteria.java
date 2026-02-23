package com.exam.exam.specification;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.exam.exam.dto.CriteriaDTO;
import com.exam.exam.entity.CustomerEntity;

/**
 * 針對 CustomerEntity 的動態查詢規格實作
 */
public class CustomerCriteria {

    public static Specification<CustomerEntity> buildSearchSpecification(CriteriaDTO criteriaDTO) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (criteriaDTO == null) {
                return cb.conjunction();
            }

            // --- 精確匹配欄位 (Equal) ---

            // 訂單編號
            if (criteriaDTO.getOrderId() != null) {
                predicates.add(cb.equal(root.get("orderId"), criteriaDTO.getOrderId()));
            }

            // 身分證字號 (通常為唯一值，建議精確匹配)
            if (StringUtils.hasText(criteriaDTO.getIdNum())) {
                predicates.add(cb.equal(root.get("idNum"), criteriaDTO.getIdNum()));
            }

            // 性別
            if (StringUtils.hasText(criteriaDTO.getGender())) {
                predicates.add(cb.equal(root.get("gender"), criteriaDTO.getGender()));
            }

            // 學歷
            if (StringUtils.hasText(criteriaDTO.getEducation())) {
                predicates.add(cb.equal(root.get("education"), criteriaDTO.getEducation()));
            }

            // 年資
            if (criteriaDTO.getYear() != null && criteriaDTO.getYear() != 0) {
                predicates.add(cb.equal(root.get("year"), criteriaDTO.getYear()));
            }

            // --- 模糊查詢欄位 (Like) ---

            // 中文姓名
            if (StringUtils.hasText(criteriaDTO.getChineseName())) {
                predicates.add(cb.like(root.get("chineseName"), "%" + criteriaDTO.getChineseName() + "%"));
            }

            // 電子郵件
            if (StringUtils.hasText(criteriaDTO.getEmail())) {
                predicates.add(cb.like(root.get("email"), "%" + criteriaDTO.getEmail() + "%"));
            }

            // 地址相關 (戶籍與現居)
            if (StringUtils.hasText(criteriaDTO.getAddress1())) {
                predicates.add(cb.like(root.get("address1"), "%" + criteriaDTO.getAddress1() + "%"));
            }
            if (StringUtils.hasText(criteriaDTO.getAddress2())) {
                predicates.add(cb.like(root.get("address2"), "%" + criteriaDTO.getAddress2() + "%"));
            }

            // --- 聯繫資訊 (可依需求決定 Equal 或 Like) ---

            // 郵遞區號
            if (StringUtils.hasText(criteriaDTO.getZipCode1())) {
                predicates.add(cb.equal(root.get("zipCode1"), criteriaDTO.getZipCode1()));
            }
            if (StringUtils.hasText(criteriaDTO.getZipCode2())) {
                predicates.add(cb.equal(root.get("zipCode2"), criteriaDTO.getZipCode2()));
            }

            // 電話與手機 (這裡範例使用精確匹配，若要支援部分號碼搜尋可改為 like)
            if (StringUtils.hasText(criteriaDTO.getTelephone1())) {
                predicates.add(cb.equal(root.get("telephone1"), criteriaDTO.getTelephone1()));
            }
            if (StringUtils.hasText(criteriaDTO.getTelephone2())) {
                predicates.add(cb.equal(root.get("telephone2"), criteriaDTO.getTelephone2()));
            }
            if (StringUtils.hasText(criteriaDTO.getMobile())) {
                predicates.add(cb.equal(root.get("mobile"), criteriaDTO.getMobile()));
            }

            // 合併所有 Predicate
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}