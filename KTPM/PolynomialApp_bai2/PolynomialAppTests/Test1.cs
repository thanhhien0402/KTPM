using Microsoft.VisualStudio.TestTools.UnitTesting;
using PolynomialApp_bai2;
using System;
using System.Collections.Generic;

namespace PolynomialAppTests
{
    [TestClass]
    public class Test1
    {
       
        [TestMethod]
        public void Constructor_N_Negative()
        {
            try
            {
                List<int> a = new List<int> { 1, 2 };
                Polynomial p = new Polynomial(-1, a);

                Assert.Fail("Expected ArgumentException was not thrown");
            }
            catch (ArgumentException)
            {
                Assert.IsTrue(true);
            }
        }

        [TestMethod]
        public void Constructor_WrongCoefficientCount()
        {
            try
            {
                List<int> a = new List<int> { 1, 2 };
                Polynomial p = new Polynomial(2, a);

                Assert.Fail("Expected ArgumentException was not thrown");
            }
            catch (ArgumentException)
            {
                Assert.IsTrue(true);
            }
        }

     
        [TestMethod]
        public void Constructor_ListNull()
        {
            try
            {
                Polynomial p = new Polynomial(2, null);

                Assert.Fail("Expected NullReferenceException was not thrown");
            }
            catch (NullReferenceException)
            {
                Assert.IsTrue(true);
            }
        }

        [TestMethod]
        public void Constructor_ValidData()
        {
            List<int> a = new List<int> { 1, 2, 3 };
            Polynomial p = new Polynomial(2, a);

            Assert.IsNotNull(p);
        }

        [TestMethod]
        public void Cal_ValidInput()
        {
            // P(x) = 1 + 2x + 3x^2
            List<int> a = new List<int> { 1, 2, 3 };
            Polynomial p = new Polynomial(2, a);

            int result = p.Cal(2);

            Assert.AreEqual(17, result);
        }

   
        [TestMethod]
        public void Cal_X_Zero()
        {
            List<int> a = new List<int> { 5, 4, 3 };
            Polynomial p = new Polynomial(2, a);

            int result = p.Cal(0);

            Assert.AreEqual(5, result);
        }
    }
}
