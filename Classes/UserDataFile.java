
package lt.Blogsite.Classes;

import java.util.ArrayList;

public class UserDataFile extends DataFile {
    
    private ArrayList<User> users = null;

    int parsingLine = 0;
    int linesToParse = 0;
    
    public UserDataFile(String fileName) {
        super(fileName);
        users = new ArrayList<User>();
        load();
    }
    
    public ArrayList<User> getUsers() {
        return (ArrayList<User>)users.clone();
    }

    @Override
    public void load() {
        super.load();
        
        parsingLine = 0;
        linesToParse = data.size();
        while (parsingLine < linesToParse) {
            String str = data.get(parsingLine);
            try {
                parseUserSection(str);
            } catch (DataFoundException e) {
                // ...
            }
            parsingLine++;
        }
    }
    
    private void parseUserSection(String line) throws DataFoundException {
        if (line.equals(User.USER_DATA_STARTS)) {
            User user = new User();
            try {
                parsingLine++;
                while (parsingLine < linesToParse) {
                    String str = data.get(parsingLine);
                    if (str.equals(User.USER_DATA_ENDS)) {
                        throw new UserSectionEndsException(User.USER_DATA_ENDS);
                    }
                    try {
                        parseUserLoginSection(str, user);
                        parseUserPasswordSection(str, user);
                        parseUserEmailSection(str, user);
                    } catch (DataFoundException e) {
                        // ...
                    }
                    parsingLine++;
                }
                throw new DataFoundException();
            } catch (UserSectionEndsException e) {
                throw new DataFoundException();
            } finally {
                users.add(user);
            }
        }
    }
    
    private void parseUserLoginSection(String line, User user) throws UserSectionEndsException, DataFoundException {
        if (line.equals(User.USER_LOGIN)) {
            parsingLine++;
            if (parsingLine < linesToParse) {
                String str = data.get(parsingLine);
                if (str.equals(User.USER_DATA_ENDS)) {
                    throw new UserSectionEndsException(User.USER_DATA_ENDS);
                }
                if (isSectionStartEnd(str) == true) {
                    str = "";
                    parsingLine--;
                }
                user.setLogin(str);
            }
            throw new DataFoundException(User.USER_LOGIN);
        }
    }
    
    private void parseUserPasswordSection(String line, User user) throws UserSectionEndsException, DataFoundException {
        if (line.equals(User.USER_PASSWORD)) {
            parsingLine++;
            if (parsingLine < linesToParse) {
                String str = data.get(parsingLine);
                if (str.equals(User.USER_DATA_ENDS)) {
                    throw new UserSectionEndsException(User.USER_DATA_ENDS);
                }
                if (isSectionStartEnd(str) == true) {
                    str = "";
                    parsingLine--;
                }
                user.setPassword(str);
            }
            throw new DataFoundException(User.USER_PASSWORD);
        }
    }
    
    private void parseUserEmailSection(String line, User user) throws UserSectionEndsException, DataFoundException {
        if (line.equals(User.USER_EMAIL)) {
            parsingLine++;
            if (parsingLine < linesToParse) {
                String str = data.get(parsingLine);
                if (str.equals(User.USER_DATA_ENDS)) {
                    throw new UserSectionEndsException(User.USER_DATA_ENDS);
                }
                if (isSectionStartEnd(str) == true) {
                    str = "";
                    parsingLine--;
                }
                user.setEmail(str);
            }
            throw new DataFoundException(User.USER_EMAIL);
        }
    }
    
    private boolean isSectionStartEnd(String line) {
        return (line.startsWith(User.SECTION_STARTS_WITH)) && (line.endsWith(User.SECTION_ENDS_WITH));
    }
    
}
