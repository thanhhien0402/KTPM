using System;

namespace StudentScholarshipApp_bai5
{
    public class Student
    {
        public string Id { get; set; }
        public string Name { get; set; }
        public string Address { get; set; }

        public double Score1 { get; set; }
        public double Score2 { get; set; }
        public double Score3 { get; set; }

        public double Average()
        {
            return (Score1 + Score2 + Score3) / 3;
        }

        public bool IsEligibleForScholarship()
        {
            if (Score1 < 5 || Score2 < 5 || Score3 < 5)
                return false;

            return Average() >= 8.0;
        }
    }
}
