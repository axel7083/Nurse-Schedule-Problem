package utils;

import models.Formation;
import models.Instance;
import models.Learner;

import java.util.Arrays;
import java.util.Comparator;

public class InstanceUtils {
    public static Instance formatInstance(Instance instance) {

        Arrays.sort(instance.formations, new Comparator<Formation>() {
            public int compare(Formation o1, Formation o2) {
                if(o1.day != o2.day)
                    return Integer.compare(o1.day,o2.day);
                else
                    return Integer.compare(o1.startHour,o2.startHour);
            }
        });

        Learner[] learners = new Learner[instance.formations.length];

        for (int i = 0; i < instance.formations.length; i++) {
            learners[i] = instance.learners[instance.formations[i].id];

            instance.formations[i].id = i;
            learners[i].id = i;
        }

        instance.learners = learners;

        return instance;
    }

}
