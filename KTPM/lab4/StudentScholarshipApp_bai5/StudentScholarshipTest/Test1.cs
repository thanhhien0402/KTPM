using Microsoft.VisualStudio.TestTools.UnitTesting;
using StudentScholarshipApp_bai5;

namespace StudentScholarshipAppTest
{
    [TestClass]
    public class StudentTest
    {
        [TestMethod]
        public void Scholarship_ValidStudent_ReturnTrue()
        {
            Student s = new Student
            {
                Score1 = 8,
                Score2 = 8.5,
                Score3 = 9
            };

            Assert.IsTrue(s.IsEligibleForScholarship());
        }

        [TestMethod]
        public void Scholarship_AverageBelow8_ReturnFalse()
        {
            Student s = new Student
            {
                Score1 = 7,
                Score2 = 8,
                Score3 = 8
            };

            Assert.IsFalse(s.IsEligibleForScholarship());
        }

        [TestMethod]
        public void Scholarship_HasScoreBelow5_ReturnFalse()
        {
            Student s = new Student
            {
                Score1 = 9,
                Score2 = 4,
                Score3 = 9
            };

            Assert.IsFalse(s.IsEligibleForScholarship());
        }

        [TestMethod]
        public void Scholarship_AverageExactly8_ReturnTrue()
        {
            Student s = new Student
            {
                Score1 = 8,
                Score2 = 8,
                Score3 = 8
            };

            Assert.IsTrue(s.IsEligibleForScholarship());
        }
    }
}
