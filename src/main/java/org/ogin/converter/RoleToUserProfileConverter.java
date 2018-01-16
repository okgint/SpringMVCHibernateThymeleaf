package org.ogin.converter;

import org.ogin.entity.UserProfile;
import org.ogin.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author ogin
 */
@Component
public class RoleToUserProfileConverter implements Converter<Object, UserProfile> {
    @Autowired
    private UserProfileService userProfileService;

    @Override
    public UserProfile convert(Object element) {
        Integer id = Integer.parseInt((String) element);
        UserProfile profile = userProfileService.findById(id);
        return profile;
    }
}
