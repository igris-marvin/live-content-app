import dayjs, { Dayjs } from 'dayjs';;
import utc from 'dayjs/plugin/utc'
import customParseFormat from 'dayjs/plugin/customParseFormat'

dayjs.extend(utc);
dayjs.extend(customParseFormat);


export const convertDateToUTC = (
    date: Dayjs
) => {

    return date.utc().format();
}

export const convertUTCToDate = (
    utcStringDate: string
) => {

    return dayjs(utcStringDate).utc();
}

export const formatStringTime = (datestr: string) => {

    return dayjs(datestr, 'HH:mm:ss').format('h:mm A');
}

export const calculateNights = (
    startDate: string, 
    endDate: string
): number => {

    const start = dayjs(startDate);
    const end = dayjs(endDate);

    // Calculate the difference in days
    const differenceInDays = end.diff(start, 'day');

    return differenceInDays;
}