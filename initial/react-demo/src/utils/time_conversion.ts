import dayjs, { Dayjs } from "dayjs";
import { convertDateToUTC, convertUTCToDate } from "./date_conversion";

export function convertDateToTime(
    date: Dayjs
): string {
    return date.format('HH:mm:ss'); // Extracts time with hours, minutes, and seconds
}

export function convertTimeToDate(
    time: string // HH:mm:ss
) {
    return convertUTCToDate(convertDateToUTC(dayjs()).replace(/T[\d:]+Z/, `T${time}Z`));
}