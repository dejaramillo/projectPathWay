package com.exampleractivemongo.reactivemongo.repo;

import com.exampleractivemongo.reactivemongo.domain.Student;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface StudentReactiveRepository extends ReactiveCrudRepository<Student,String> {
}
