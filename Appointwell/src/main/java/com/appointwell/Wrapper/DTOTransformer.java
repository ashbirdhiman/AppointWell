package com.appointwell.Wrapper;

import com.appointwell.POJO.Doctor;

import javax.print.Doc;

public class DTOTransformer {

    public static DoctorWrapper toDoctorDTO(Doctor doctor) {
        DoctorWrapper doctorDTO = new DoctorWrapper(doctor.getId()
                ,doctor.getName(),doctor.getSpecialization(), doctor.getStatus());

        return doctorDTO;
    }

//    public static Hospital toHospitalDTO(Hospital hospital) {
//        HospitalDTO hospitalDTO = new HospitalDTO();
//        hospitalDTO.setId(hospital.getId());
//        hospitalDTO.setName(hospital.getName());
//        hospitalDTO.setLatitude(hospital.getLatitude());
//        hospitalDTO.setLongitude(hospital.getLongitude());
//
//        // Map doctors to DoctorBasicDTO to avoid circular reference
//        List<DoctorBasicDTO> doctorBasicList = hospital.getDoctors().stream()
//            .map(DTOTransformer::toDoctorBasicDTO)
//            .collect(Collectors.toList());
//        hospitalDTO.setDoctors(doctorBasicList);
//
//        return hospitalDTO;
//    }
//
//    public static DoctorBasicDTO toDoctorBasicDTO(Doctor doctor) {
//        DoctorBasicDTO doctorBasicDTO = new DoctorBasicDTO();
//        doctorBasicDTO.setId(doctor.getId());
//        doctorBasicDTO.setName(doctor.getName());
//        doctorBasicDTO.setSpecialization(doctor.getSpecialization());
//        return doctorBasicDTO;
//    }
}
