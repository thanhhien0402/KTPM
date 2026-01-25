using Microsoft.VisualStudio.TestTools.UnitTesting;
using PowerApp_bai1;

namespace PowerAppTests
{
    [TestClass]
    public class PowerUnitTest
    {
        // Test n = 0
        [TestMethod]
        public void TestPower_N_Equals_Zero()
        {
            double result = MathUtils.Power(5, 0);
            Assert.AreEqual(1.0, result);
        }

        // Test n > 0
        [TestMethod]
        public void TestPower_N_Positive()
        {
            double result = MathUtils.Power(2, 3);
            Assert.AreEqual(8.0, result);
        }

        // Test n = 1
        [TestMethod]
        public void TestPower_N_Equals_One()
        {
            double result = MathUtils.Power(4, 1);
            Assert.AreEqual(4.0, result);
        }

        // Test n < 0
        [TestMethod]
        public void TestPower_N_Negative()
        {
            double result = MathUtils.Power(2, -2);
            Assert.AreEqual(0.25, result, 0.0001);
        }

        // Test số thực
        [TestMethod]
        public void TestPower_Double_Value()
        {
            double result = MathUtils.Power(2.5, 2);
            Assert.AreEqual(6.25, result, 0.0001);
        }
    }
}
