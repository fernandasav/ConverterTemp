import java.util.InputMismatchException;
import java.util.Scanner;

public class TemperatureConverter {

    private static double getTemp() {
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.println("Digite uma temperatura: ");
            try {
                return input.nextDouble();
            } catch (InputMismatchException e) {
                System.err.println("A temperatura foi digitada incorretamente. Digite apenas números");
            }
        }
    }


    public static void main(String[] args) {
        int totalConverter, totalTemp;
        TemperatureUnity unityInput, unityOutput;
        double temp, resultado, totalEntrada = 0, totalSaida = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("-------------------------------------------");
        System.out.println("| Bem vindo ao conversor de temperaturas!  |");
        System.out.println("-------------------------------------------");
        System.out.println("Quantas temperaturas voce deseja converter?");
        totalConverter = input.nextInt();
        totalTemp = totalConverter;

        while (totalConverter != 0) {
            unityInput = getUnityTemp("entrada");
            unityOutput = getUnityTemp("saída");

            temp = getTemp();

            totalEntrada = totalEntrada + temp;
            switch (unityInput) {
                case CELSIUS:
                    resultado = fromCelsiusTransform(unityOutput, temp);
                    break;
                case FAHRENHEIT:
                    resultado = fromFahrenheitTransform(unityOutput, temp);
                    break;
                case KELVIN:
                    resultado = fromKelvinTransform(unityOutput, temp);
                    break;
                default:
                    resultado = 0;
            }

            totalSaida = totalSaida + resultado;

            System.out.printf("O resultado da conversão é %f", resultado);
            System.out.println("\n");
            totalConverter = totalConverter - 1;
        }

        System.out.printf("A media de entrada é %f", (totalEntrada/totalTemp));
        System.out.println("\n");
        System.out.printf("A media de saida é %f", (totalSaida/totalTemp));
        System.out.println("\n");
        System.out.println("Obrigado por usar o conversor de temperatura");

    }

    private static TemperatureUnity getUnityTemp(String temperaturaEntradaSaida) {
        while(true) {
            Scanner input = new Scanner(System.in);
            System.out.println("------------------------------------------------------------------------------------------------");
            System.out.println("| Celsius                                                                                      |");
            System.out.println("| Fahrenheit                                                                                   |");
            System.out.println("| Kelvin                                                                                       |");
            System.out.println("------------------------------------------------------------------------------------------------");
            System.out.println("Digite a unidade de temperatura de " + temperaturaEntradaSaida + ":");
            String typeString = input.nextLine();
            try {
                return TemperatureUnity.valueOf(typeString.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.err.println("A unidade foi digitada incorretamente. Escolha uma das opções abaixo para continuar:");
            }
        }
    }

    private static double fromFahrenheitTransform(TemperatureUnity type, double temp) {
        if (type == TemperatureUnity.CELSIUS) {
            return ((temp * 1.8) + 32);
        } else if(type == TemperatureUnity.KELVIN) {
            return (temp * 1.8) - 459.67;
        } else {
            return temp;
        }
    }

    private static double fromCelsiusTransform(TemperatureUnity type, double temp) {
        if (type == TemperatureUnity.FAHRENHEIT) {
            return (temp - 32) / 1.8;
        } else if (type == TemperatureUnity.KELVIN) {
            return temp - 273.15;
        } else {
            return temp;
        }
    }

    private static double fromKelvinTransform(TemperatureUnity type, double temp){
        if (type == TemperatureUnity.CELSIUS) {
            return temp + 273;
        } else if (type == TemperatureUnity.FAHRENHEIT) {
            return ((temp - 32) * 5 / 9) + 273;
        } else {
            return temp;
        }
    }
}
