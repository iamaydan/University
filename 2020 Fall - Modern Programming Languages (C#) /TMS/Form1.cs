using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Lesson1
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }
        int i = 1;
        private void button1_Click(object sender, EventArgs e)
        {
            string text1 = textBox1.Text;
            string text2 = textBox2.Text;
            string text3 = textBox3.Text;
            if (text1 != "" && text2 != "" && text3 != "")
            {
                richTextBox1.Text += i + ". " + text1 + " " + text2 + " " + text3 + '\n';
                textBox1.Clear();
                textBox2.Clear();
                textBox3.Clear();
                i += 1;
                label4.Visible = false;
            }
            else label4.Visible = true;
                   }

        private void button2_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void button3_Click(object sender, EventArgs e)
        {
            textBox1.Clear();
            textBox2.Clear();
            textBox3.Clear();
        }

        private void button4_Click(object sender, EventArgs e)
        {
            richTextBox1.Clear();
            i = 1;
        }
    }
}
