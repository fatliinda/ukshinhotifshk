package com.example.ukshinhotifshk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course saveCourse(Course course) {
        // Validate input
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null");
        }
        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public Course updateCourse(Long id, Course course) {
        // Validate input
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null");
        }

        // Check if the course with the given ID exists
        Optional<Course> optionalExistingCourse = courseRepository.findById(id);
        if (optionalExistingCourse.isPresent()) {
            Course existingCourse = optionalExistingCourse.get();
            // Update properties
            existingCourse.setTitle(course.getTitle());
            existingCourse.setCredits(course.getCredits());
            existingCourse.setSchedule(course.getSchedule());
            existingCourse.setProfessor(course.getProfessor());
            existingCourse.setLocation(course.getLocation());
            return courseRepository.save(existingCourse);
        } else {
            throw new IllegalArgumentException("Course not found with ID: " + id);
        }
    }
}
