import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'filterUsers'
})
export class FilterUsersPipe implements PipeTransform {

    transform(value: any, args?: any): any {
        if (!args) {
            return value;
        }
        return value.filter(
            item => item.username.toLowerCase().indexOf(args.toLowerCase()) > -1
            || item.email.toLowerCase().indexOf(args.toLowerCase()) > -1
        );
    }
}