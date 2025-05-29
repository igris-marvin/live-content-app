import { Convert } from "easy-currencies";

export const convertCurrency = async (
    price: number,
    fromCurrency: string = 'ZAR',
    toCurrency: string = 'ZAR'
) => {

    const value = await Convert(price)
                .from(fromCurrency ? fromCurrency : "ZAR")
                .to(toCurrency);
    
    return value;
}

export const formatPrice = (
    price: number, 
    currency: string = 'ZAR',
    locale: string = 'en-ZA', 
    // user currency, acc currency
): string => {

    return new Intl.NumberFormat(locale, {
        style: 'currency',
        currency: currency
    }).format(price);

};

export const formatPercentage = (value: number, locale: string = 'en-US'): string => {
    return new Intl.NumberFormat(locale, {
      style: 'percent',
      minimumFractionDigits: 2,  // To show 2 decimal places
      maximumFractionDigits: 2   // To show 2 decimal places
    }).format(value);
}

export const calcPercentageOff = (
    price: number,
    discountedPrice: number
) => {
    
    return formatPercentage(
        (discountedPrice/(price + discountedPrice))
    );
}