using System;
using System.Text;

namespace RadixApp
{
    public class Radix
    {
        private int number;

        public Radix(int number)
        {
            if (number < 0)
                throw new ArgumentException("Incorrect Value");
            this.number = number;
        }

        public string ConvertDecimalToAnother(int radix = 2)
        {
            if (radix < 2 || radix > 16)
                throw new ArgumentException("Invalid Radix");

            if (number == 0)
                return "0";

            const string chars = "0123456789ABCDEF";
            StringBuilder result = new StringBuilder();

            int temp = number;
            while (temp > 0)
            {
                result.Insert(0, chars[temp % radix]);
                temp /= radix;
            }

            return result.ToString();
        }
    }
}
