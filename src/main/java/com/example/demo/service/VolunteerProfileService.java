public interface VolunteerProfileService {
    VolunteerProfile registerVolunteer(RegisterRequest request); [cite: 183]
    VolunteerProfile updateAvailability(Long volunteerId, String availabilityStatus); [cite: 184]
    List<VolunteerProfile> getAvailableVolunteers(); [cite: 185]
}