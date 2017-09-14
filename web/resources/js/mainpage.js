$(document).ready(function () {
    $('#jstree').jstree({
        "plugins": ["sort", "themes", "json_data", "dnd", "contextmenu"],

        'core': {
            'check_callback': true,
            "themes" : {
                "variant" : "large"
            },
            'data': {
                'url': function (node) {
                    return node.id === '#' ? '/tree/?id=%23' : '/tree/?id=' + node.id;
                },
            }

        },
        'contextmenu': {
            'items': contextmenu
        }
    });

    function contextmenu(node) {

        var tree = $('#jstree').jstree(true);

        var items = {
            "Create": {
                "separator_before": false,
                "separator_after": true,
                "label": "Create",
                "action": function (obj) {
                    let name = window.prompt("Input new node name", "");
                    if(name !== null && name !== ""){
                        $.ajax({
                            type: "GET",
                            url: "/check_name",
                            data: {
                                "name": name,
                                "parent": node.id
                            },
                            success : function(resp)
                            {
                                if(resp === true){
                                    tree.refresh_node(node);
                                }
                            }
                        });
                    }
                }
            },
            "Rename": {
                "separator_before": false,
                "separator_after": false,
                "label": "Rename",
                "action": function (obj) {
                    tree.edit(node, node.text, function (node) {
                        $.ajax({
                            type: "GET",
                            url: "/update",
                            data: {
                                "id": node.id,
                                "name": node.text
                            },
                        });
                        tree.refresh_node(tree.get_parent(node));
                    });

                }
            },

            "Remove": {
                "separator_before": true,
                "separator_after": false,
                "label": "Remove",
                "action": function (obj) {
                    $.ajax({
                        type: "GET",
                        url: "/delete",
                        data: {
                            "id": node.id
                        },
                        success: function (data) {
                            if (data == true) {
                                tree.refresh_node(tree.get_parent(node));
                            }
                        }
                    });
                }
            }
        };
        return items;
    }

    $('#jstree').on('after_close.jstree', function (event, data) {
        // Flag it to be reloaded on reopen:
        data.node.state.loaded = false;
    });
});

function updateTree() {
    var tree = $('#jstree').jstree(true);
    tree.refresh();
};