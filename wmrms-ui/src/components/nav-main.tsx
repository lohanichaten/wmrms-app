import { IconCirclePlusFilled, IconMail, type Icon } from "@tabler/icons-react";

import { Button } from "@/components/ui/button";
import {
  SidebarGroup,
  SidebarGroupContent,
  SidebarMenu,
  SidebarMenuButton,
  SidebarMenuItem,
} from "@/components/ui/sidebar";
import { useState } from "react";
import { ChevronDown, ChevronRight } from "lucide-react";

type NavItem = {
  title: string;
  icon?: Icon;
  url?: string;
  items?: { title: string; url: string; icon: Icon }[];
};

export function NavMain({ items }: { items: NavItem[] }) {
  const [expandedMenus, setExpandedMenus] = useState<string[]>([""]);

  const toggleExpand = (title: string) => {
    setExpandedMenus((prev) =>
      prev.includes(title) ? prev.filter((t) => t !== title) : [...prev, title]
    );
  };

  return (
    <SidebarGroup>
      <SidebarGroupContent className="flex flex-col gap-2">
        <SidebarMenu>
          {items.map((item) => {
            const isExpandable = Array.isArray(item.items);
            const isExpanded = expandedMenus.includes(item.title);

            return (
              <SidebarMenuItem key={item.title}>
                {isExpandable ? (
                  <>
                    <SidebarMenuButton
                      tooltip={item.title}
                      onClick={() => toggleExpand(item.title)}
                      className="flex justify-between items-center w-full"
                    >
                      <div className="flex items-center gap-2">
                        {item.icon && <item.icon className="w-4 h-4 shrink-0" />}
                        <span>{item.title}</span>
                      </div>
                      {isExpanded ? (
                        <ChevronDown className="h-4 w-4 opacity-60" />
                      ) : (
                        <ChevronRight className="h-4 w-4 opacity-60" />
                      )}
                    </SidebarMenuButton>

                    {isExpanded && (
                      <div className="pl-6">
                      <SidebarMenu className="space-y-1">
                        {item.items!.map((subItem) => (
                          <SidebarMenuItem key={subItem.title}>
                            <SidebarMenuButton tooltip={subItem.title} asChild>
                              <a href={subItem.url}>
                                {subItem.icon && <subItem.icon/>}
                                <span className="text-sm">{subItem.title}</span>
                              </a>
                            </SidebarMenuButton>
                          </SidebarMenuItem>
                        ))}
                      </SidebarMenu>
                      </div>
                    )}
                  </>
                ) : (
                  <SidebarMenuButton tooltip={item.title} asChild>
                    <a href={item.url}>
                      {item.icon && <item.icon />}
                      <span>{item.title}</span>
                    </a>
                  </SidebarMenuButton>
                )}
              </SidebarMenuItem>
            );
          })}
        </SidebarMenu>
      </SidebarGroupContent>
    </SidebarGroup>
  );
}
