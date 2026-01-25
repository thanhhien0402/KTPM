using Microsoft.VisualStudio.TestTools.UnitTesting;
using RadixApp;
using System;

namespace RadixAppTests
{
    [TestClass]
    public class RadixTest
    {
        [TestMethod]
        public void Constructor_Number_Negative()
        {
            try
            {
                Radix r = new Radix(-5);
                Assert.Fail();
            }
            catch (ArgumentException)
            {
                Assert.IsTrue(true);
            }
        }

        [TestMethod]
        public void Convert_Invalid_Radix_Low()
        {
            try
            {
                Radix r = new Radix(10);
                r.ConvertDecimalToAnother(1);
                Assert.Fail();
            }
            catch (ArgumentException)
            {
                Assert.IsTrue(true);
            }
        }

        [TestMethod]
        public void Convert_Invalid_Radix_High()
        {
            try
            {
                Radix r = new Radix(10);
                r.ConvertDecimalToAnother(20);
                Assert.Fail();
            }
            catch (ArgumentException)
            {
                Assert.IsTrue(true);
            }
        }

        [TestMethod]
        public void Convert_To_Binary()
        {
            Radix r = new Radix(10);
            string result = r.ConvertDecimalToAnother(2);
            Assert.AreEqual("1010", result);
        }

        [TestMethod]
        public void Convert_To_Octal()
        {
            Radix r = new Radix(10);
            string result = r.ConvertDecimalToAnother(8);
            Assert.AreEqual("12", result);
        }

        [TestMethod]
        public void Convert_To_Hex()
        {
            Radix r = new Radix(255);
            string result = r.ConvertDecimalToAnother(16);
            Assert.AreEqual("FF", result);
        }
    }
}
