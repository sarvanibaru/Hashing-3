// Time Complexity : O(U*S), U= number of users, S= number of songs
// Space Complexity : O(S)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
/*
First, we map song to genre for faster access using genreMap. Then for every user, we count how many songs
belong to each genre. The genre/genres with the highest count is added to the result list for that user.
 */
public static Map<String, List<String>> favoritegenre(Map<String, List<String>> userMap, Map<String, List<String>> genreMap) {
    Map<String,String> songToGenreMap = new HashMap<>();

    for(String genre : genreMap.keySet()) {
        List<String> songList = genreMap.get(genre);
        for(String song : songList) {
            songToGenreMap.put(song, genre);
        }
    }

    Map<String, List<String>> result = new HashMap<>();
    for(String user : userMap.keySet()) {
        Map<String, Integer> frqMap = new HashMap<>();
        int max = 0;
        result.put(user, new ArrayList<>());

        List<String> songs = userMap.get(user);
        for(String song : songs) {
            String genre = songToGenreMap.get(song);
            frqMap.put(genre, frqMap.getOrDefault(genre, 0) + 1);
            if(max < frqMap.get(genre)) {
                result.get(user).clear();
                result.get(user).add(genre);
            }
            else if(max == frqMap.get(genre)){
                result.get(user).add(genre);
            }
            max = Math.max(max, frqMap.get(genre));
        }
    }
    return result;
}

public static void main(String[] args) {
    HashMap<String, List<String>> userSongs = new HashMap<>();

    userSongs.put("David", Arrays.asList(new String[]{"song1", "song2", "song3", "song4", "song8"}));

    userSongs.put("Emma", Arrays.asList(new String[]{"song5", "song6", "song7"}));

    HashMap<String, List<String>> songGenres = new HashMap<>();

    songGenres.put("Rock", Arrays.asList(new String[]{"song1", "song3"}));

    songGenres.put("Dubstep", Arrays.asList(new String[]{"song7"}));

    songGenres.put("Techno", Arrays.asList(new String[]{"song2", "song4"}));

    songGenres.put("Pop", Arrays.asList(new String[]{"song5", "song6"}));

    songGenres.put("Jazz", Arrays.asList(new String[]{"song8", "song9"}));

    Map<String, List<String>> res = favoritegenre(userSongs, songGenres);

    System.out.println(res);
}