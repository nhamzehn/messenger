package jo.edu.ju.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jo.edu.ju.model.Profile;

public class ProfileService {

	private Map<String, Profile> profiles = new HashMap<>();

	public ProfileService() {
		profiles.put("hmz", new Profile(1L, "hmz", "Hamzeh", "Asefan"));
		profiles.put("sha", new Profile(2L, "sha", "Shaher", "Hatem"));
	}

	public List<Profile> getAllProfiles() {
		return new ArrayList<Profile>(profiles.values());
	}

	public Profile getProfile(String profileName) {
		return profiles.get(profileName);
	}

	public Profile addProfile(Profile profile) {
		profile.setId((long) (profiles.size() + 1));
		profiles.put(profile.getProfileName(), profile);

		return profile;
	}

	public Profile updateProfile(Profile profile) {
		if (profile.getProfileName().isEmpty()) {
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}

	public Profile removeProfile(String profileName) {
		return profiles.remove(profileName);
	}


}
