$(document).ready(function () {
    $('#jstree').jstree({
        "plugins": ["sort", "themes", "unique", "json_data", "dnd", "contextmenu"],

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
                        var node_data = {};
                        node_data["id"] = node.id;
                        node_data["text"] = node.text;
                        node_data["parent"] = tree.get_parent(node);
                        $.ajax({
                            type: "POST",
                            contentType: "application/json",
                            url: "/update",
                            data: JSON.stringify(node_data),
                            dataType: 'json'
                        }).always(function(){
                            tree.refresh_node(tree.get_parent(node));
                        });
                        //tree.refresh_node(tree.get_parent(node));
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
                            if (data === true) {
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
        // Reload on open -> update
        data.node.state.loaded = false;
    });

    $(document).on('move_node.jstree', function (e, data) {

        let node_data = {};
        node_data["id"] = $('#' + data.node.id).find('a')[0].id;
        node_data["text"] = _node.text;
        node_data["parent"] = $('#' + data.parent).find('a')[0].id;
        alert(node_data.id + node_data.text + node_data.parent);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/update",
            data: JSON.stringify(node_data),
            dataType: 'json'
        }).always(function(){
            tree.refresh_node(tree.get_parent(_node));
        });
    });
});


function updateTree() {
    var tree = $('#jstree').jstree(true);
    tree.refresh();
}