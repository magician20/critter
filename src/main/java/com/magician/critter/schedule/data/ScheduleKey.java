// package com.udacity.jdnd.course3.critter.schedule.data;

// import java.io.Serializable;
// import java.util.Objects;

// import javax.persistence.*;


// //Composite Key , if our keys will stay unique 
// //but that will never happen because date have impact on myabe same employe and pet happen
// @Embeddable
// public class ScheduleKey implements Serializable  {
//     @Column(name = "employee_id")
//     Long employeeId;

//     @Column(name = "pet_id")
//     Long petId;


//     public ScheduleKey() {
//     }

//     public Long getEmployeeId() {
//         return this.employeeId;
//     }

//     public void setEmployeeId(Long employeeId) {
//         this.employeeId = employeeId;
//     }

//     public Long getPetId() {
//         return this.petId;
//     }

//     public void setPetId(Long petId) {
//         this.petId = petId;
//     }

//     @Override
//     public boolean equals(Object o) {
//         if (o == this)
//             return true;
//         if (!(o instanceof ScheduleKey)) {
//             return false;
//         }
//         ScheduleKey scheduleKey = (ScheduleKey) o;
//         return Objects.equals(employeeId, scheduleKey.employeeId) && Objects.equals(petId, scheduleKey.petId);
//     }

//     @Override
//     public int hashCode() {
//         return Objects.hash(employeeId, petId);
//     }

// }
