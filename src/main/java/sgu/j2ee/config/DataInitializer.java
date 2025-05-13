package sgu.j2ee.config;

import java.util.HashSet;
import java.util.Set;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import sgu.j2ee.model.Permission;
import sgu.j2ee.model.Prescriptions;
import sgu.j2ee.model.Role;
import sgu.j2ee.model.User;
import sgu.j2ee.repository.PermissionRepository;
import sgu.j2ee.repository.PrescriptionRepository;
import sgu.j2ee.repository.RoleRepository;
import sgu.j2ee.repository.UserRepository;
import sgu.j2ee.model.Family;
import sgu.j2ee.model.Medications;
import sgu.j2ee.model.TestResults;
import sgu.j2ee.model.Documents;
import sgu.j2ee.repository.FamilyRepository;
import sgu.j2ee.repository.TestResultsRepository;
import sgu.j2ee.repository.DocumentRepository;
import sgu.j2ee.repository.MedicationRepository;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner{

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final FamilyRepository familyRepository;
    private final TestResultsRepository testResultsRepository;
    private final DocumentRepository documentRepository;
    private final MedicationRepository medicationRepository;
    private final PrescriptionRepository prescriptionRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if(userRepository.count() == 0 && permissionRepository.count() == 0 && roleRepository.count() == 0) {
            Set<Permission> permissions = Set.of(
                Permission.builder().permissionName("FULL_ACCESS").build(), 
                Permission.builder().permissionName("VIEW").build(),
                Permission.builder().permissionName("ADD").build(),
                Permission.builder().permissionName("UPDATE").build(),
                Permission.builder().permissionName("DELETE").build(),
                Permission.builder().permissionName("UPLOAD").build()
            );

            permissionRepository.saveAll(permissions);

            Set<Permission> fullPermission = new HashSet<>();
            fullPermission.add(permissionRepository.findByPermissionName("FULL_ACCESS"));

            Role roleAdmin = Role.builder().roleName("ADMIN").permissions(fullPermission).build();

            Role roleDoctor = Role.builder().roleName("DOCTOR").permissions(fullPermission).build();

            Set<Permission> patientPermission = new HashSet<>();
            patientPermission.add(permissionRepository.findByPermissionName("VIEW"));

            Role rolePatient = Role.builder().roleName("PATIENT").permissions(patientPermission).build();

            roleRepository.save(roleAdmin);
            roleRepository.save(roleDoctor);
            roleRepository.save(rolePatient);

            User adminUser = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin123")) 
                .role(roleAdmin)
                .build();

            userRepository.save(adminUser);

            User doctorUser = User.builder()
                .username("DrThanh")
                .password(passwordEncoder.encode("drthanh"))
                .role(roleDoctor)
                .build();

            userRepository.save(doctorUser);

            User patientUser = User.builder()
                .username("patient1")
                .password(passwordEncoder.encode("patient1"))
                .role(rolePatient)
                .build();

            userRepository.save(patientUser);

            if (testResultsRepository.count() == 0) {
                TestResults testResult1 = TestResults.builder()
                    .user(patientUser)
                    .testType("Blood Test")
                    .result("Normal")
                    .datePerformed(LocalDate.now().minusDays(10))
                    .labName("HealthLab")
                    .build();

                TestResults testResult2 = TestResults.builder()
                    .user(patientUser)
                    .testType("X-Ray")
                    .result("No Issues")
                    .datePerformed(LocalDate.now().minusDays(5))
                    .labName("City Diagnostics")
                    .build();

                testResultsRepository.save(testResult1);
                testResultsRepository.save(testResult2);
            }

            if (testResultsRepository.count() == 0) {
                TestResults testResult1 = TestResults.builder()
                    .user(patientUser)
                    .testType("Blood Test")
                    .result("Normal")
                    .datePerformed(LocalDate.now().minusDays(10))
                    .labName("HealthLab")
                    .build();

                TestResults testResult2 = TestResults.builder()
                    .user(patientUser)
                    .testType("X-Ray")
                    .result("No Issues")
                    .datePerformed(LocalDate.now().minusDays(5))
                    .labName("City Diagnostics")
                    .build();

                testResultsRepository.save(testResult1);
                testResultsRepository.save(testResult2);
            }

            if (prescriptionRepository.count() == 0) {
                Prescriptions prescription1 = Prescriptions.builder()
                    .user(patientUser)
                    .doctor(doctorUser)
                    .prescriptionDate(new Date()) // Changed to Date
                    .notes("Take medication with food.")
                    .build();

                Prescriptions prescription2 = Prescriptions.builder()
                    .user(patientUser)
                    .doctor(doctorUser)
                    .prescriptionDate(new Date()) // Changed to Date
                    .notes("Avoid alcohol while taking this medication.")
                    .build();

                prescriptionRepository.save(prescription1);
                prescriptionRepository.save(prescription2);
            }
            if (medicationRepository.count() == 0) {
            Medications medication1 = Medications.builder()
                .medicationName("Aspirin")
                .dosage("500mg")
                .frequency("Once a day")
                .quantity("30 tablets")
                .instructions("Take with water after meals.")
                .startDate(new Date())
                .endDate(new Date(System.currentTimeMillis() + 7L * 24 * 60 * 60 * 1000)) // 7 days later
                .prescription(prescriptionRepository.findById(1L).orElse(null)) // Example prescription
                .build();

            Medications medication2 = Medications.builder()
                .medicationName("Paracetamol")
                .dosage("500mg")
                .frequency("Twice a day")
                .quantity("20 tablets")
                .instructions("Take after meals.")
                .startDate(new Date())
                .endDate(new Date(System.currentTimeMillis() + 5L * 24 * 60 * 60 * 1000)) // 5 days later
                .prescription(prescriptionRepository.findById(1L).orElse(null)) // Example prescription
                .build();

            Medications medication3 = Medications.builder()
                .medicationName("Ibuprofen")
                .dosage("200mg")
                .frequency("Three times a day")
                .quantity("15 tablets")
                .instructions("Take with food to avoid stomach upset.")
                .startDate(new Date())
                .endDate(new Date(System.currentTimeMillis() + 10L * 24 * 60 * 60 * 1000)) // 10 days later
                .prescription(prescriptionRepository.findById(2L).orElse(null))
                .build();

            Medications medication4 = Medications.builder()
                .medicationName("Amoxicillin")
                .dosage("250mg")
                .frequency("Twice a day")
                .quantity("14 capsules")
                .instructions("Complete the full course of antibiotics.")
                .startDate(new Date())
                .endDate(new Date(System.currentTimeMillis() + 7L * 24 * 60 * 60 * 1000)) // 7 days later
                .prescription(prescriptionRepository.findById(2L).orElse(null))
                .build();

            Medications medication5 = Medications.builder()
                .medicationName("Metformin")
                .dosage("500mg")
                .frequency("Once a day")
                .quantity("30 tablets")
                .instructions("Take with meals to reduce side effects.")
                .startDate(new Date())
                .endDate(new Date(System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000)) // 30 days later
                .prescription(prescriptionRepository.findById(1L).orElse(null))
                .build();

            Medications medication6 = Medications.builder()
                .medicationName("Atorvastatin")
                .dosage("10mg")
                .frequency("Once a day")
                .quantity("30 tablets")
                .instructions("Take in the evening for best results.")
                .startDate(new Date())
                .endDate(new Date(System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000)) // 30 days later
                .prescription(prescriptionRepository.findById(1L).orElse(null))
                .build();

            Medications medication7 = Medications.builder()
                .medicationName("Omeprazole")
                .dosage("20mg")
                .frequency("Once a day")
                .quantity("14 capsules")
                .instructions("Take before meals.")
                .startDate(new Date())
                .endDate(new Date(System.currentTimeMillis() + 14L * 24 * 60 * 60 * 1000)) // 14 days later
                .prescription(prescriptionRepository.findById(2L).orElse(null))
                .build();

            Medications medication8 = Medications.builder()
                .medicationName("Losartan")
                .dosage("50mg")
                .frequency("Once a day")
                .quantity("30 tablets")
                .instructions("Take at the same time every day.")
                .startDate(new Date())
                .endDate(new Date(System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000)) // 30 days later
                .prescription(prescriptionRepository.findById(1L).orElse(null))
                .build();

            medicationRepository.saveAll(Set.of(
                medication1, medication2, medication3, medication4,
                medication5, medication6, medication7, medication8
            ));
        }
        }

        
    }

}
