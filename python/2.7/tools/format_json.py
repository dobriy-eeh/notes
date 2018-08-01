import codecs
import json
import os.path
import sys

def format_json(file_name):
    if not os.path.exists(file_name):
        print >> sys.stderr, file_name + " not exist"
        return

    if not os.path.isfile(file_name):
        print >> sys.stderr, file_name + " is not file"
        return

    with open(file_name) as data_file:
        json_data = json.load(data_file)

    with codecs.open(file_name, 'w', encoding='utf-8') as out:
        out.write(json.dumps(json_data, indent=4, ensure_ascii=False, sort_keys=True))
        out.write('\n')

    print file_name + ' formatted'


format_json('test.json')
