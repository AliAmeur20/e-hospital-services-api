package com.example.eHospitalServices.Services;

import com.example.eHospitalServices.Models.ConsumableMD;
import com.example.eHospitalServices.Repositories.ConsumableMDRepo;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class Utils {
    private final ConsumableMDRepo consumableMDRepo;

    public Utils(ConsumableMDRepo consumableMDRepo) {
        this.consumableMDRepo = consumableMDRepo;
    }

    public ConsumableMD checkAndGetCMD (Long id){
        Optional<ConsumableMD> consumableMD = consumableMDRepo.findById(id);
        if (consumableMD.isPresent()){
            return consumableMD.get();
        }else{
            throw new NoSuchElementException();
        }
    }

    public static <T> Specification<T> getEqualSpec(String property, Object value) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(getPath(root, property), value);
    }
    public static <T> Specification<T> getLikeSpec(String property, String value) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get(property)), "%" + value + "%");
    }

    public static <T> Specification<T> getNullSpec(String property) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isNull(getPath(root, property));
    }

    public static <T> Specification<T> getLikeCMDSpec(String property, String value) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("consumableMD").get(property)), "%" + value + "%");
    }

    public static <T> Path<T> getPath(Root<T> root, String nestedProperty) {
        String[] properties = nestedProperty.split("\\.");
        Path<T> path = root.get(properties[0]);
        for (int i = 1; i < properties.length; i++) {
            path = path.get(properties[i]);
        }

        return path;
    }
}
