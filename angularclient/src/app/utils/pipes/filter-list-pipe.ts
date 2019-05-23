import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'filterList'
})
export class FilterListPipe implements PipeTransform {

    transform(value: any, args?: any): any {
        if (!args) {
            return value;
        }
        return value.filter(
            item => item.band.toLowerCase().indexOf(args.toLowerCase()) > -1
            || item.title.toLowerCase().indexOf(args.toLowerCase()) > -1
        );
    }
}