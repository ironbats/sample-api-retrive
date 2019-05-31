package br.com.amaro.dto;



public enum CategoriesEnum {

    NEUTRO,
    VELUDO,
    COURO,
    BASICS,
    FESTA,
    WORKWEAR,
    INVERNO,
    BOHO,
    ESTAMPAS,
    BALADA,
    COLORIDO,
    CASUAL,
    LISO,
    MODERNO,
    PASSEIO,
    METAL,
    VIAGEM,
    DELICADO,
    DESCOLADO,
    ELASTANO;



    public static boolean matchWithCategories(String code) {
        for (CategoriesEnum categoriesEnum : CategoriesEnum.values()) {

            boolean check = checkTags(code);

            if (!check && code.equalsIgnoreCase(categoriesEnum.toString())) {
                return true;
            }
        }
        return false;
    }

    public static CategoriesEnum ramdomCategoris()
    {
        return values()[(int ) (Math.random() * values().length)];
    }

    private static boolean checkTags(String code){

        return code.equalsIgnoreCase(CategoriesEnum.NEUTRO.toString())
                || code.equalsIgnoreCase(CategoriesEnum.VELUDO.toString())
                || code.equalsIgnoreCase(CategoriesEnum.BALADA.toString());
    }
}


