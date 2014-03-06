/**
 * Created by andrew on 3/6/14.
 */
public class ParsingCmd
{
    String codeOfCommand;
    String parameter;
    String barcode;

    public ParsingCmd(String txt)
    {
        codeOfCommand = "";
        parameter = "";
        barcode = "";

        if(txt.charAt(0)<'0' || txt.charAt(0)>'9')  //не цифра, значит код команды и параметр
        {
            codeOfCommand = txt.substring(0,1);
            if(txt.charAt(1)<'0' || txt.charAt(1)>'9') //если и второй символ не цифра, значит параметр текстовый
            {
                parameter = txt.substring(1);
            }
            else //параметр числовой или ошибочный
            {
                for(int i=1;i<txt.length();i++) //проверяем все остальные символы
                {
                    if(txt.charAt(i)<'0' || txt.charAt(i)>'9') //не цифра,значит ошибочная команда
                    {
                        codeOfCommand = "";
                        parameter = "";
                        return;
                    }
                }
                //все символы параметра цифры, значит проверим контрольную сумму
                int sum=0;
                for(int i=1;i<txt.length()-1;i++)
                {
                    sum += (txt.charAt(i)-'0');
                }
                String sumstr = (new Integer(sum)).toString();
                if(txt.charAt(txt.length()-1) == sumstr.charAt(sumstr.length()-1)) //контрольная сумма совпадает
                    parameter = txt.substring(1,txt.length()-1);
                else //ошибочная команда
                {
                    codeOfCommand = "";
                    parameter = "";
                    barcode = "";
                }
            }
        }
        else //цифра, значит штрихкод или ошибочная команда
        {
            for(int i=1;i<txt.length();i++) //проверяем все остальные символы
            {
                if(txt.charAt(i)<'0' || txt.charAt(i)>'9') //не цифра,значит ошибочная команда
                    return;
            }
            barcode = txt; //все цифры, значит это штрихкод
        }
    }

    public String retCodeCmd()
    {
        return(codeOfCommand);
    }

    public String retParameter()
    {
        return(parameter);
    }

    public String retBarcode()
    {
        return(barcode);
    }
}
